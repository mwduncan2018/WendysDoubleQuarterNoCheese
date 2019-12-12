package com.duncan.autoutilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.duncan.safeflightautoframe.pom.DestinationsPage;
import com.duncan.safeflightautoframe.pom.Driver;

class ImageSolutionTests {
	String TEST_IMAGE_FOLDER = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\AutoUtilities\\src\\test\\resources\\ImageSolution\\";
	String IMAGE_CANVAS_DIRECTORY = TEST_IMAGE_FOLDER + "image_canvas";
	String NEW_YORK_IMAGE = TEST_IMAGE_FOLDER + "new_york_carousel.jpg";
	String NEW_YORK_SMALL_IMAGE = TEST_IMAGE_FOLDER + "new_york_small_carousel.jpg";
	String NEW_YORK_SUPER_SMALL_IMAGE = TEST_IMAGE_FOLDER + "new_york_super_small_carousel.jpg";
	String DENVER_IMAGE = TEST_IMAGE_FOLDER + "denver_carousel.jpg";
	String LONDON_IMAGE = TEST_IMAGE_FOLDER + "london_carousel.jpg";

	@Test
	void test_new_york_super_small_image() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToNewYorkCarouselImage();

		ImageSolution sol = new ImageSolution(NEW_YORK_SUPER_SMALL_IMAGE);

		if (sol.getCenterPoint() == null) {
			System.out.println("New York small image was not found.");
		} else {
			System.out.println("[New York Small] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

	@Test
	void test_new_york_small_image() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToNewYorkCarouselImage();

		ImageSolution sol = new ImageSolution(NEW_YORK_SMALL_IMAGE);

		if (sol.getCenterPoint() == null) {
			System.out.println("New York small image was not found.");
		} else {
			System.out.println("[New York Small] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

	@Test
	void test_new_york_image() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToNewYorkCarouselImage();

		ImageSolution sol = new ImageSolution(NEW_YORK_IMAGE);

		if (sol.getCenterPoint() == null) {
			System.out.println("New York image was not found.");
		} else {
			System.out.println("[New York] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

	@Test
	void test_london_image() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToLondonCarouselImage();

		ImageSolution sol = new ImageSolution(LONDON_IMAGE);

		if (sol.getCenterPoint() == null) {
			System.out.println("London image was not found.");
		} else {
			System.out.println("[London] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

	@Test
	void test_denver_image() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToDenverCarouselImage();

		ImageSolution sol = new ImageSolution(DENVER_IMAGE);

		if (sol.getCenterPoint() == null) {
			System.out.println("Denver image was not found.");
		} else {
			System.out.println("[Denver] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

	@Test
	void test_image_directory_1() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToDenverCarouselImage();

		ImageSolution sol = new ImageSolution(IMAGE_CANVAS_DIRECTORY);

		if (sol.getCenterPoint() == null) {
			System.out.println("IMAGE_CANVAS_DIRECTORY... nothing found.");
		} else {
			System.out.println("[IMAGE_CANVAS_DIRECTORY] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

	@Test
	void test_image_directory_2() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToLondonCarouselImage();

		ImageSolution sol = new ImageSolution(IMAGE_CANVAS_DIRECTORY);

		if (sol.getCenterPoint() == null) {
			System.out.println("IMAGE_CANVAS_DIRECTORY... nothing found.");
		} else {
			System.out.println("[IMAGE_CANVAS_DIRECTORY] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

	@Test
	void test_image_directory_3() {
		Driver.initialize();
		DestinationsPage.goTo();
		DestinationsPage.goToNewYorkCarouselImage();

		ImageSolution sol = new ImageSolution(IMAGE_CANVAS_DIRECTORY);

		if (sol.getCenterPoint() == null) {
			System.out.println("IMAGE_CANVAS_DIRECTORY... nothing found.");
		} else {
			System.out.println("[IMAGE_CANVAS_DIRECTORY] Center Point = " + sol.getCenterPoint().toString());
		}

		assertTrue(sol.getCenterPoint() != null);
		Driver.close();
	}

}
