package com.bdd.mavenbddproject.tests.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions
(
tags = "", 
features = {"./resources/features/HomePage.feature"}, 
glue = {"com.bdd.mavenbddproject.tests.HomePageTests"},
plugin = {}
)
    
public class HomePageTests extends AbstractTestNGCucumberTests 
{
    
	
}




