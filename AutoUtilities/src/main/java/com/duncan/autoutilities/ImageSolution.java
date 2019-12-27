package com.duncan.autoutilities;

import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
	public void practice(String ss, String t, String m) throws Exception {
		// This must be loaded to use 'new Mat()'
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		this.captureScreenshot();
		
		ss = SCREENSHOT_PATH;

		// ===================================
		// Part 1
		// ===================================
		Boolean useMask = false;
		Integer matchMethod = Imgproc.TM_CCOEFF_NORMED;

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
		} else {
			matchLoc = mmr.maxLoc;
		}
		
		System.out.println("mmr.maxVal = " + mmr.maxVal);

		Imgproc.rectangle(imgDisplay, matchLoc, new Point(matchLoc.x + temp.cols(), matchLoc.y + temp.rows()),
				new Scalar(0, 0, 200), 2, 8, 0);
		Imgproc.rectangle(result, matchLoc, new Point(matchLoc.x + temp.cols(), matchLoc.y + temp.rows()),
				new Scalar(0, 0, 200), 2, 8, 0);
		
		Imgcodecs.imwrite(IMAGE_SOLUTION_FOLDER_PATH + "practiceImgDisplay.jpg", imgDisplay);
		Imgcodecs.imwrite(IMAGE_SOLUTION_FOLDER_PATH + "practiceResult.jpg", imgDisplay);
		
		/*
		Image tmpImg = HighGui.toBufferedImage(imgDisplay);
		ImageIcon icon = new ImageIcon(tmpImg);
		jLabelImgDisplay.setIcon(icon);
		
		result.convertTo(result, CvType.CV_8UC1, 255.0);
		tmpImg = HighGui.toBufferedImage(result);
		icon = new ImageIcon(tmpImg);
		resultDisplay.setIcon(icon);
		*/
		
	}

	private static String IMAGE_SOLUTION_FOLDER_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\AutoUtilities\\src\\main\\resources\\ImageSolution\\";

	// Used to capture the screenshot, the screenshot with a red rectangle around
	// the match, and the match coordinates
	private static String SCREENSHOT_PATH = IMAGE_SOLUTION_FOLDER_PATH + "screenshot.jpg";
	private static String RED_RECTANGLE_PATH = IMAGE_SOLUTION_FOLDER_PATH + "red_rectangle.jpg";
	private static String MATCH_COORDINATES_PATH = IMAGE_SOLUTION_FOLDER_PATH + "match_coordinates.txt";

	private String path = null;
	private List<String> templates = null;
	private Boolean matchFound = false;
	private Integer templateMatchIndex = null;
	private List<Point> matchPoints = null;

	private Point upperLeftPoint = null;
	private Point upperRightPoint = null;
	private Point lowerLeftPoint = null;
	private Point lowerRightPoint = null;
	private Point centerPoint = null;

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

	public ImageSolution(String path) {
		this.path = path;
		//this.captureScreenshot();

	}

	public void constructor() {
		captureScreenshot();
		initializeTemplates();

		matchPoints = new ArrayList<Point>();
		for (int i = 0; i < templates.size(); i++) {
			if (templateMatchIndex == null) {
				//runOpenCvWithPython(templates.get(i));
				//readPythonOutputFile();
				if (matchFound == true) {
					templateMatchIndex = i;
				}
			}
		}

		calculatePoints();

	}

	private void initializeTemplates() {
		templates = new ArrayList<String>();
		File file = new File(path);
		if (file.isFile()) {
			templates.add(path);
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

	private void calculatePoints() {
		if (matchPoints.size() > 0) {

			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			Mat matScreenshot = Imgcodecs.imread(SCREENSHOT_PATH);
			Mat matTemplate = Imgcodecs.imread(templates.get(templateMatchIndex));

			// Upper left point
			this.upperLeftPoint = matchPoints.get(0);

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

			// Draw a rectangle where the template was found
			// For debugging purposes!
			Imgproc.rectangle(matScreenshot, upperLeftPoint, lowerRightPoint, new Scalar(0, 0, 255));
			Imgcodecs.imwrite(IMAGE_SOLUTION_FOLDER_PATH + "result.jpg", matScreenshot);
		}
	}

}
