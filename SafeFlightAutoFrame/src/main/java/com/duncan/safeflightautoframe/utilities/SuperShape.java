package com.duncan.safeflightautoframe.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import org.opencv.core.Point;

public class SuperShape {

	public static void draw(List<Point> points) {
		System.out.println("point size = " + ((Integer) points.size()).toString());
		if (points.size() > 0) {
			try {
				Robot robot = new Robot();
				robot.mouseMove((int) points.get(0).x, (int) points.get(0).y);
				robot.delay(200);
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.delay(200);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				robot.delay(200);		
				robot.mousePress(MouseEvent.BUTTON1_MASK);
				robot.delay(200);
				for (int i = 1; i < points.size(); i++) {
					robot.mouseMove((int) points.get(i).x, (int) points.get(i).y);
					robot.delay(200);
				}
				robot.mouseRelease(MouseEvent.BUTTON1_MASK);
				robot.delay(200);
				robot.mouseMove(0, 0);
				robot.delay(200);
			} catch (AWTException e) {
				e.printStackTrace();
			}

		}
	}

}
