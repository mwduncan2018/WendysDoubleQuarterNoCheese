package com.duncan.safeflightautoframe.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.opencv.core.Point;

public class SuperRobot {

	public static void clickPoint(Point p) {
		try {
			Robot robot = new Robot();
			robot.mouseMove((int) p.x, (int) p.y);
			robot.delay(200);
			robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
			robot.delay(100);
			robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
			robot.delay(200);
			robot.mouseMove(0, 0);
			robot.delay(200);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void sendKeys(String keys) {
		String text = keys;
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(150);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(150);
			robot.keyRelease(KeyEvent.VK_V);
			robot.delay(150);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(300);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}
}
