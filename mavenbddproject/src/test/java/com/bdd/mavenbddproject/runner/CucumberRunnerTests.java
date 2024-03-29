package com.bdd.mavenbddproject.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions
(
tags = "", 
features = {"./resources/features/fxFeatureFile.feature"}, 
glue = {"com.bdd.mavenbddproject"},
plugin = {}
)
    
public class CucumberRunnerTests extends AbstractTestNGCucumberTests 
{
    
}