package com.duncan.autoutilities;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageSolution {
	private static String PYTHON_IMAGE_SOLUTION_EXE_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\AutoUtilities\\src\\main\\java\\com\\duncan\\autoutilities\\image_solution.py";
	private static String IMAGE_SOLUTION_FOLDER_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\AutoUtilities\\src\\main\\resources\\ImageSolution\\";

	// These 2 variables are used to interact with the Python code
	private static String MATCH_COORDINATES_PATH = IMAGE_SOLUTION_FOLDER_PATH + "match_coordinates.txt";
	private static String SCREENSHOT_PATH = IMAGE_SOLUTION_FOLDER_PATH + "screenshot.jpg";

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

		captureScreenshot();
		initializeTemplates();

		matchPoints = new ArrayList<Point>();
		for (int i = 0; i < templates.size(); i++) {
			if (templateMatchIndex == null) {
				runOpenCvWithPython(templates.get(i));
				readPythonOutputFile();
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

	private void runOpenCvWithPython(String templatePath) {
		// Run Python script
		System.out.println("Running Python Image Search");
		System.out.println("  " + IMAGE_SOLUTION_FOLDER_PATH + "screenshot.jpg");
		System.out.println("  " + templatePath);
		System.out.println("  " + IMAGE_SOLUTION_FOLDER_PATH + "match_coordinates.txt");

		try {
			ProcessBuilder pb = new ProcessBuilder("python", PYTHON_IMAGE_SOLUTION_EXE_PATH, templatePath,
					IMAGE_SOLUTION_FOLDER_PATH + "screenshot.jpg",
					IMAGE_SOLUTION_FOLDER_PATH + "match_coordinates.txt");
			Process p = pb.start();
			Thread.sleep(750);
			p.destroy();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	private void readPythonOutputFile() {
		// Read the output file of the Python script into an ArrayList of Points
		matchPoints = new ArrayList<Point>();
		try (BufferedReader bufRead = Files.newBufferedReader(Paths.get(MATCH_COORDINATES_PATH))) {
			String line;
			Point point = null;
			while ((line = bufRead.readLine()) != null) {
				if (line.length() > 0) {
					if (line.contains("None")) {
						System.out.println("  NO MATCHES!");
						point = null;
					} else {
						matchFound = true;
						line = line.trim().replace("(", "").replace(")", "").replace(" ", "");
						Integer x = new Integer(line.replaceAll(",[0-9]*", ""));
						Integer y = new Integer(line.replaceAll("[0-9]*,", ""));
						point = new Point(x, y);
						matchPoints.add(point);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("  " + "Number of match points = " + ((Integer) this.matchPoints.size()).toString());

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
