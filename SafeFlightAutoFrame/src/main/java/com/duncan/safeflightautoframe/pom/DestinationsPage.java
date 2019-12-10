package com.duncan.safeflightautoframe.pom;

import org.opencv.core.Point;
import org.openqa.selenium.By;

import com.duncan.safeflightautoframe.utilities.ImageSolution;

public class DestinationsPage extends BasePage {

	static By TITLE = By.id("pageName");

	static By NEW_YORK_CAROUSEL = By.id("NewYorkCarousel");
	static By LONDON_CAROUSEL = By.id("LondonCarousel");
	static By DENVER_CAROUSEL = By.id("DenverCarousel");

	static String NEW_YORK_IMAGE = IMAGES_FOLDER + "DestinationsPage\\new_york_carousel.jpg";
	static String LONDON_IMAGE = IMAGES_FOLDER + "DestinationsPage\\london_carousel.jpg";
	static String DENVER_IMAGE = IMAGES_FOLDER + "DestinationsPage\\denver_carousel.jpg";

	public static void goTo() {
		Driver.instance.navigate().to(URL + "Destinations");
	}

	public static void goToDenverCarouselImage() {
		Driver.instance.findElement(DENVER_CAROUSEL).click();
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void goToNewYorkCarouselImage() {
		Driver.instance.findElement(NEW_YORK_CAROUSEL).click();
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void goToLondonCarouselImage() {
		Driver.instance.findElement(LONDON_CAROUSEL).click();
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean verifyIsAt() {
		if (Driver.instance.findElement(TITLE).getText().equals("Destinations")) {
			return true;
		}
		return false;
	}

	public static boolean verifyDenverCarouselImage() {
		Point point = new ImageSolution(DENVER_IMAGE).getCenterPoint();
		if (point != null) {
			return true;
		}
		return false;
	}

	public static boolean verifyNewYorkCarouselImage() {
		Point point = new ImageSolution(NEW_YORK_IMAGE).getCenterPoint();
		if (point != null) {
			return true;
		}
		return false;
	}

	public static boolean verifyLondonCarouselImage() {
		Point point = new ImageSolution(LONDON_IMAGE).getCenterPoint();
		if (point != null) {
			return true;
		}
		return false;
	}
}
