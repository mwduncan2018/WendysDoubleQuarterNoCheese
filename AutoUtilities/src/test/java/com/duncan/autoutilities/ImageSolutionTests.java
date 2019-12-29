package com.duncan.autoutilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.opencv.core.Point;


class ImageSolutionTests {
	String TEST_IMAGE_FOLDER = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\SafeFlightAutoFrame\\src\\main\\resources\\images\\pom\\";
	String IMAGE_CANVAS_DIRECTORY = TEST_IMAGE_FOLDER + "DestinationsPage\\any_image";
	String NEW_YORK_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\new_york_carousel.jpg";
	String NEW_YORK_SMALL_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\new_york_small_carousel.jpg";
	String NEW_YORK_SUPER_SMALL_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\new_york_super_small_carousel.jpg";
	String DENVER_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\denver_carousel.jpg";
	String LONDON_IMAGE = TEST_IMAGE_FOLDER + "DestinationsPage\\london_carousel.jpg";
	String NAV_DESTINATIONS = TEST_IMAGE_FOLDER + "DestinationsPage\\nav_destinations.png";
	String NAV_LOGOUT= TEST_IMAGE_FOLDER + "DestinationsPage\\nav_logout.png";

	@Test
	void test_nav_destionations() throws Exception {
		ImageSolution sol = new ImageSolution(NAV_DESTINATIONS);
		System.out.println(sol.getUpperLeftPoint());
		System.out.println(sol.debugThreshold());
		System.out.println("___________________________________________________");
	}
	
	@Test
	void test_nav_logout() throws Exception {
		ImageSolution sol = new ImageSolution(NAV_LOGOUT);
		if (sol.getUpperLeftPoint() == null) {
			System.out.println("NAV LOGOUT SUCCESSFULLY NOT FOUND :)");
		}
		System.out.println(sol.debugThreshold());		
		System.out.println("___________________________________________________");
	}

	/*
	@Test
	@Ignore
	void test_practice_01() {
		try {
			String FIND_ME = NEW_YORK_SUPER_SMALL_IMAGE;
			ImageSolution sol = new ImageSolution("not currently used");
			System.out.println("(Junit).. ImagePath = " + FIND_ME + "\n");
			//sol.practice(null, FIND_ME, null, 0); // TM_CCOEFF
			//sol.practice(null, FIND_ME, null, 1); // TM_CCOEFF_NORMED
			//sol.practice(null, FIND_ME, null, 2); // TM_CCORR doesn't work
			sol.practice(null, FIND_ME, null, 3); // TM_CCORR_NORMED
			//sol.practice(null, FIND_ME, null, 4); // TM_SQDIFF
			//sol.practice(null, FIND_ME, null, 5); // TM_SQDIFF_NORMED			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
