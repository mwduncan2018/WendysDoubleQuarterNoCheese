package com.duncan.safeflightautoframe.utilities;

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

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageSolution {

	private String PROJECT_LOCATION = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\";
	private String IMAGE_FOLDER = PROJECT_LOCATION + "SafeFlightAutoFrame\\src\\main\\resources\\images\\pom\\";
	private String screenshot= IMAGE_FOLDER + "screenshot.jpg";
	private String writeLocation = IMAGE_FOLDER + "match_coordinates.txt";
	private String pythonImageSolutionExecutable = PROJECT_LOCATION + "SafeFlightAutoFrame\\src\\main\\java\\com\\duncan\\safeflightautoframe\\utilities\\image_solution.py";
	
	private String template;
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

	public ImageSolution(String template) {
		this.template = IMAGE_FOLDER + template;
		findImage();
	}

	private void takeScreenshot() {
		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "jpg", new File(IMAGE_FOLDER + "screenshot.jpg"));
		} catch (IOException | AWTException e) {
			e.printStackTrace();
		}
	}

	private void runPythonScript() {
		// Run Python script
		System.out.println("Running Python Image Search");
		System.out.println("  " + this.screenshot);
		System.out.println("  " + this.template);
		System.out.println("  " + this.writeLocation);
		
		try {
			ProcessBuilder pb = new ProcessBuilder("python", this.pythonImageSolutionExecutable, this.template,
					this.screenshot, this.writeLocation);
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void readPythonOutputFile() {
		// Read the output file of the Python script into an ArrayList of Points
		this.matchPoints = new ArrayList<Point>();
	    try (BufferedReader bufRead = Files.newBufferedReader(Paths.get(this.writeLocation))) {
	        String line;
	        Point point = null;
	        while ((line = bufRead.readLine()) != null) {
	            if (line.length() > 0) {
	            	if (line.contains("None")) {
	            		System.out.println("  NO MATCHES!");
	            		point = null;
	            	}
	            	else {
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
		System.out.println("  " + "Number of match points = " + ((Integer)this.matchPoints.size()).toString());
	}

	private void calculatePoints() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat matScreenshot = null;
		Mat matTemplate = null;
		matScreenshot = Imgcodecs.imread(this.screenshot);
		matTemplate = Imgcodecs.imread(this.template);
		
		if (matchPoints.size() > 0) {
			// Upper left point
			this.upperLeftPoint = matchPoints.get(0);

			// Upper right point
			this.upperRightPoint = new Point(upperLeftPoint.x + matTemplate.cols(), upperLeftPoint.y);

			// Lower left point
			this.lowerLeftPoint = new Point(upperLeftPoint.x, upperLeftPoint.y + matTemplate.rows());

			// Lower right point
			this.lowerRightPoint = new Point(upperLeftPoint.x + matTemplate.cols(), upperLeftPoint.y + matTemplate.rows());

			// Calculate the center point
			double centerXCoordinate = Math.round((upperRightPoint.x - upperLeftPoint.x) / 2) + upperLeftPoint.x;
			double centerYCoordinate = Math.round((lowerLeftPoint.y - upperLeftPoint.y) / 2) + upperLeftPoint.y;
			this.centerPoint = new Point(centerXCoordinate, centerYCoordinate);

			// Draw a rectangle where the template was found
			// For debugging purposes!
			Imgproc.rectangle(matScreenshot, upperLeftPoint, lowerRightPoint, new Scalar(0, 0, 255));
			Imgcodecs.imwrite(IMAGE_FOLDER + "result.jpg", matScreenshot);			
		}
	}
	
	private void findImage() {
		takeScreenshot();
		runPythonScript();
		readPythonOutputFile();
		calculatePoints();
	}
	
}
