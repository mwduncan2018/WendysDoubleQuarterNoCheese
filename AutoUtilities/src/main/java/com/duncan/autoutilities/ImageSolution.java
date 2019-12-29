package com.duncan.autoutilities;

import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageSolution {
	// TM_CCORR_NORMED
	// If mmr.maxVal >= .97, we have a match
	// If mmr.maxVal < .97, there is no match

	private static String IMAGE_SOLUTION_FOLDER_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\AutoUtilities\\src\\main\\resources\\ImageSolution\\";

	// Used to capture the screenshot, the screenshot with a red rectangle around
	// the match, and the match coordinates
	private static String SCREENSHOT_PATH = IMAGE_SOLUTION_FOLDER_PATH + "screenshot.jpg";
	private static String RED_RECTANGLE_PATH = IMAGE_SOLUTION_FOLDER_PATH + "red_rectangle.jpg";
	private static String MATCH_COORDINATES_PATH = IMAGE_SOLUTION_FOLDER_PATH + "match_coordinates.txt";

	private String templatePath = null;
	private List<String> templates = null;
	private Boolean matchFound = false;
	private Integer templateMatchIndex = null;
	private List<Point> matchPoints = null;
	private Point matchPoint = null;

	private Point upperLeftPoint = null;
	private Point upperRightPoint = null;
	private Point lowerLeftPoint = null;
	private Point lowerRightPoint = null;
	private Point centerPoint = null;
	
	private Double mmrMinVal = null;
	private Double mmrMaxVal = null;

	// for debugging
	public Double debugThreshold() {
		if (mmrMaxVal != null) {
			return mmrMaxVal;
		} else {
			return mmrMinVal;
		}
	}
	
	// for debugging
	public String debugMatchPoint() {
		if (matchPoint == null) {
			return null;
		} else {
			return matchPoint.toString();
		}
		
	}
	
	public Point getCenterPoint() {
		return this.centerPoint;
	}

	public Point getUpperLeftPoint() {
		return this.upperLeftPoint;
	}

	public Point getUpperRightPoint() {
		return this.upperRightPoint;
	}

	public Point getLowerLeftPoint() {
		return this.lowerLeftPoint;
	}

	public Point getLowerRightPoint() {
		return this.lowerRightPoint;
	}

	public ImageSolution(String templatePath) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		this.templatePath = templatePath;
		this.captureScreenshot();
		this.initializeTemplates();

		matchPoints = new ArrayList<Point>();
		for (int i = 0; i < templates.size(); i++) {
			if (templateMatchIndex == null) {
				this.templateMatch(templates.get(i));
				if (matchFound == true) {
					templateMatchIndex = i;
				}
			}
		}

		if (templateMatchIndex != null) {
			this.calculatePoints();			
		}
		
		if (this.getCenterPoint() != null) {
			this.writeRedRectangleFile();
			this.writeMatchPointsFile();
		}

	}

	
	private void templateMatch(String tPath) throws Exception {
		Mat img = Imgcodecs.imread(SCREENSHOT_PATH, Imgcodecs.IMREAD_COLOR);
		Mat temp = Imgcodecs.imread(tPath, Imgcodecs.IMREAD_COLOR);

		Integer matchMethod = Imgproc.TM_CCORR_NORMED;
		// Available Algorithms:
		// Imgproc.TM_CCOEFF
		// Imgproc.TM_CCOEFF_NORMED
		// Imgproc.TM_CCORR <--- DOES NOT WORK!! DO NOT USE IT!!
		// Imgproc.TM_CCORR_NORMED
		// Imgproc.TM_SQDIFF
		// Imgproc.TM_SQDIFF_NORMED
		
		// if one of the images cannot be read, throw an exception
		if (img.empty() || temp.empty()) {
			throw new Exception("During template matching, one of the images could not be read.");
		}

		Mat imgDisplay = new Mat();
		img.copyTo(imgDisplay);
		
		Mat result = new Mat();
		int resultCols = img.cols() - temp.cols() + 1;
		int resultRows = img.rows() - temp.rows() + 1;
		result.create(resultRows, resultCols, CvType.CV_32FC1);

		Imgproc.matchTemplate(img, temp, result, matchMethod);
		
		Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
		if (matchMethod == Imgproc.TM_SQDIFF || matchMethod == Imgproc.TM_SQDIFF_NORMED) {
			this.matchPoint = mmr.minLoc;
			this.mmrMinVal = mmr.minVal;
			// not fully implemented for Imgproc.TM_SQDIFF and Imgproc.TM_SQDIFF_NORMED
		} else {
			this.matchPoint = mmr.maxLoc;
			this.mmrMaxVal = mmr.maxVal;
			if (this.mmrMaxVal >= 0.97) {
				matchFound = true;
			}
		}
	}

	private void calculatePoints() {
		if (matchPoint != null) {
			Mat matScreenshot = Imgcodecs.imread(SCREENSHOT_PATH);
			Mat matTemplate = Imgcodecs.imread(templates.get(templateMatchIndex));

			// Upper left point
			this.upperLeftPoint = matchPoint;

			// Upper right point
			this.upperRightPoint = new Point(upperLeftPoint.x + matTemplate.cols(), upperLeftPoint.y);

			// Lower left point
			this.lowerLeftPoint = new Point(upperLeftPoint.x, upperLeftPoint.y + matTemplate.rows());

			// Lower right point
			this.lowerRightPoint = new Point(upperLeftPoint.x + matTemplate.cols(),
					upperLeftPoint.y + matTemplate.rows());

			// Calculate the center point
			double centerXCoordinate = Math.round((upperRightPoint.x - upperLeftPoint.x) / 2) + upperLeftPoint.x;
			double centerYCoordinate = Math.round((lowerLeftPoint.y - upperLeftPoint.y) / 2) + upperLeftPoint.y;
			this.centerPoint = new Point(centerXCoordinate, centerYCoordinate);
		}
	}
	
	private void writeMatchPointsFile() {
		try {
			BufferedWriter bw = Files.newBufferedWriter(Paths.get(MATCH_COORDINATES_PATH));
			bw.write(this.upperLeftPoint.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeRedRectangleFile() {
		Mat img = Imgcodecs.imread(SCREENSHOT_PATH, Imgcodecs.IMREAD_COLOR);
		Mat temp = Imgcodecs.imread(templatePath, Imgcodecs.IMREAD_COLOR);
		Imgproc.rectangle(img, upperLeftPoint, lowerRightPoint, new Scalar(0, 0, 200), 2, 8, 0);
		Imgcodecs.imwrite(RED_RECTANGLE_PATH, img);
	}
	
	private void initializeTemplates() {
		templates = new ArrayList<String>();
		File file = new File(templatePath);
		if (file.isFile()) {
			templates.add(templatePath);
		} else if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				templates.add(f.getAbsolutePath());
			}
		}
	}

	private void captureScreenshot() {
		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "jpg", new File(IMAGE_SOLUTION_FOLDER_PATH + "screenshot.jpg"));
		} catch (IOException | AWTException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	public String practiceDeprecated(String ss, String t, String m, int mm) throws Exception {
		// This must be loaded to use 'new Mat()'
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		this.captureScreenshot();
		
		ss = SCREENSHOT_PATH;

		// ===================================
		// Part 1
		// ===================================
		Boolean useMask = false;
		Integer matchMethod = null;	
		if (mm == 0) { matchMethod = Imgproc.TM_CCOEFF; }
		if (mm == 1) { matchMethod = Imgproc.TM_CCOEFF_NORMED; }
		if (mm == 2) { matchMethod = Imgproc.TM_CCORR; } // doesn't work!
		if (mm == 3) { matchMethod = Imgproc.TM_CCORR_NORMED; }
		if (mm == 4) { matchMethod = Imgproc.TM_SQDIFF; }
		if (mm == 5) { matchMethod = Imgproc.TM_SQDIFF_NORMED; }

		Mat img = new Mat();
		Mat temp = new Mat();
		Mat mask = new Mat(); // only Imgproc.TM_SQDIFF or Imgproc.TM_CCORR_NORMED can be used for a mask

		JLabel jLabelImgDisplay = new JLabel();
		JLabel resultDisplay = new JLabel();

		img = Imgcodecs.imread(ss, Imgcodecs.IMREAD_COLOR);
		temp = Imgcodecs.imread(t, Imgcodecs.IMREAD_COLOR);
		// not using a mask now

		// if one of the images cannot be read, throw an exception
		if (img.empty() || temp.empty() || (useMask && mask.empty())) {
			throw new Exception("During template matching, one of the images could not be read.");
		}

		// ===================================
		// Part 2
		// ===================================
		Mat imgDisplay = new Mat();
		img.copyTo(imgDisplay);

		Mat result = new Mat();
		int resultCols = img.cols() - temp.cols() + 1;
		int resultRows = img.rows() - temp.rows() + 1;
		result.create(resultRows, resultCols, CvType.CV_32FC1);

		if (useMask) {
			// not using a mask now
		} else {
			Imgproc.matchTemplate(img, temp, result, matchMethod);
		}
		
		// Use a threshold
		Mat thresResult = new Mat();
		// Imgproc.threshold(result, thresResult, , maxval, type)
		
		
		//Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

		Point matchLoc;
		Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

		if (matchMethod == Imgproc.TM_SQDIFF || matchMethod == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mmr.minLoc;
			System.out.println("mmr.minVal = " + mmr.minVal);
		} else {
			matchLoc = mmr.maxLoc;
			System.out.println("mmr.maxVal = " + mmr.maxVal);
		}
		System.out.println("point = " + matchLoc.toString());
		

		Imgproc.rectangle(imgDisplay, matchLoc, new Point(matchLoc.x + temp.cols(), matchLoc.y + temp.rows()),
				new Scalar(0, 0, 200), 2, 8, 0);
		Imgproc.rectangle(result, matchLoc, new Point(matchLoc.x + temp.cols(), matchLoc.y + temp.rows()),
				new Scalar(0, 0, 200), 2, 8, 0);
		
		Imgcodecs.imwrite(IMAGE_SOLUTION_FOLDER_PATH + "practiceImgDisplay.jpg", imgDisplay);
		Imgcodecs.imwrite(IMAGE_SOLUTION_FOLDER_PATH + "practiceResult.jpg", imgDisplay);
		
		return matchLoc.toString();
	}

	private void templateMatchWithMask(String tPath) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // This must be loaded to use 'new Mat()'

		Integer matchMethod = Imgproc.TM_CCORR_NORMED;
		// Available Algorithms:
		// Imgproc.TM_CCOEFF
		// Imgproc.TM_CCOEFF_NORMED
		// Imgproc.TM_CCORR <--- DOES NOT WORK!! DO NOT USE IT!!
		// Imgproc.TM_CCORR_NORMED
		// Imgproc.TM_SQDIFF
		// Imgproc.TM_SQDIFF_NORMED

		Boolean useMask = false; // mask is not implemented
		Mat mask = new Mat();
		// only Imgproc.TM_SQDIFF or Imgproc.TM_CCORR_NORMED can be used for a mask
		// mask = ... not using a mask now

		Mat img = Imgcodecs.imread(SCREENSHOT_PATH, Imgcodecs.IMREAD_COLOR);
		Mat temp = Imgcodecs.imread(tPath, Imgcodecs.IMREAD_COLOR);
		
		// if one of the images cannot be read, throw an exception
		if (img.empty() || temp.empty() || (useMask && mask.empty())) {
			throw new Exception("During template matching, one of the images could not be read.");
		}

		Mat imgDisplay = new Mat();
		img.copyTo(imgDisplay);
		
		Mat result = new Mat();
		int resultCols = img.cols() - temp.cols() + 1;
		int resultRows = img.rows() - temp.rows() + 1;
		result.create(resultRows, resultCols, CvType.CV_32FC1);

		if (useMask) {
			// not using a mask now
		} else {
			Imgproc.matchTemplate(img, temp, result, matchMethod);
		}
		
		Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
		if (matchMethod == Imgproc.TM_SQDIFF || matchMethod == Imgproc.TM_SQDIFF_NORMED) {
			this.matchPoint = mmr.minLoc;
			this.mmrMinVal = mmr.minVal;
			// not fully implemented for Imgproc.TM_SQDIFF and Imgproc.TM_SQDIFF_NORMED
		} else {
			this.matchPoint = mmr.maxLoc;
			this.mmrMaxVal = mmr.maxVal;
			if (this.mmrMaxVal >= 0.97) {
				matchFound = true;
			}
		}
		
	}
}
