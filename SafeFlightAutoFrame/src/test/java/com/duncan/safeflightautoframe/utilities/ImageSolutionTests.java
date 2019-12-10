package com.duncan.safeflightautoframe.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ImageSolutionTests {

	@Test
	void imageSolutionTest1() {
		String template = "DestinationsPage\\denver_carousel.jpg";
		ImageSolution sol = new ImageSolution(template);
		assertTrue(sol.getCenterPoint() != null);
	}

}
