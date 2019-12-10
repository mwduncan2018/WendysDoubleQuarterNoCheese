package com.duncan.safeflightbdd.hooks;

import static org.junit.Assert.*;

import org.junit.Test;

import com.duncan.safeflightautoframe.pom.Driver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before(order=99)
	public void beforeScenario() {
		Driver.initialize();
	}

	@After(order=1)
	public void afterScenario() {
		
	}

}
