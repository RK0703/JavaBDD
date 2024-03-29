package com.bdd.mavenbddproject.tests.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions
(
tags = "", 
features = {"./resources/features/fxLogin.feature"}, 
glue = {"com.bdd.mavenbddproject.tests.LoginPageTests"},
plugin = {}
)
    
public class LoginTests extends AbstractTestNGCucumberTests 
{
    
	
}




