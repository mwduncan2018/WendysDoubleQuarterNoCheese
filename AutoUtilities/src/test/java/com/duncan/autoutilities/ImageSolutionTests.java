package com.duncan.autoutilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ImageSolutionTests {
	String TEST_IMAGE_FOLDER = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\SafeFlightAutoFrame\\src\\main\\resources\\images\\pom\\";
	String IMAGE_CANVAS_DIRECTORY = TEST_IMAGE_FOLDER + "DestinationsPage\\any_image";
	String NEW_YORK_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\new_york_carousel.jpg";
	String NEW_YORK_SMALL_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\new_york_small_carousel.jpg";
	String NEW_YORK_SUPER_SMALL_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\new_york_super_small_carousel.jpg";
	String DENVER_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\denver_carousel.jpg";
	String LONDON_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\london_carousel.jpg";
	String NAV_DESTINATIONS = TEST_IMAGE_FOLDER + "DestinationsPage\\nav_destinations.png";

	@Test
	void test_practice_01() {
		try {
			ImageSolution sol = new ImageSolution("not currently used");
			System.out.println("HEY... " + NAV_DESTINATIONS);
			sol.practice(null, NAV_DESTINATIONS, null);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
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
	*/
}
