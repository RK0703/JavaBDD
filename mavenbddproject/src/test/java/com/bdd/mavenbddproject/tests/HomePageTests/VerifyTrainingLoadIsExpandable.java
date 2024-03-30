package com.bdd.mavenbddproject.tests.HomePageTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.FrontierX.BaseSettings.Settings;
import com.FrontierX.PageLibRepo.HomePageLib;
import com.FrontierX.PageLibRepo.LoginPageLib;
import com.FrontierX.Utilities.FxUtilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class VerifyTrainingLoadIsExpandable extends Settings


{
    private WebDriver driver;
    public final static int TIMEOUT = 5;
 
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 String ClassName = this.getClass().getSimpleName().toString();
	 String Activity = "Searchable Activity";
   
	// @Before
  // @Parameters({ "enivironment" })
   private void Initialize(String env)
   {
		 System.out.println("Running Class "+ClassName);
		 try
		 {
			 this.driver = DecideEnvironment(env);
			 loginObj = new LoginPageLib(driver);
			 
			 
			 if(env == "serverless")
			 {
				 homeObj = loginObj.login("ravikiran@fourthfrontier.com",FxUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Home Obj Initialized");
			 }
			 
			 else
			 {   // Regular Prod User
				 homeObj = loginObj.login("quality+automation@fourthfrontier.com",FxUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Home Obj Initialized");
			 }
		 }
		 catch (Exception e)
		 {
			 System.out.println("Failed to Initialize due to exception "+e.getMessage());
		 }
	 
   }
    
    
    @After
    public void FinishExecution() 
    {
    	 System.out.println("Ending Execution");
    	// driver.quit();
    }
    
    
    @AfterClass
    private void CloseDriverSession(ITestResult result) throws Exception
	 {
		 if (result.getStatus() == ITestResult.FAILURE) 
		 {
			 FxUtilities.createWorkFlowFolder(ClassName);
			 FxUtilities.CaptureEvidance(driver,result.getMethod().getMethodName(),result,ClassName);
			 System.out.println(result.getMethod().getMethodName()+" Test Failed Due to the reason\n"+result.getThrowable().getMessage());
		 }  
		 if(result.getStatus() == ITestResult.SUCCESS)
		 {
			 System.out.println("<-------------Passed Test case is -> " +result.getName()+"-------------->");
		 }
		// driver.quit();
	 }
	
    
    @When("User Clicks on Training load Arrow in {string}")
    public void ExpandTrainingLoadArrow(String string) throws InterruptedException
    {
    	Initialize(string);
    	homeObj.clickOnTrainingLoadDownArrow();
		FxUtilities.WaitForVisibilityOfElement(driver, "xPath", "//i[@class='fa fa-chevron-up']");
    }
    
    @Then("Verify that Days Time line is displayed")
    public void VerifyTrainingLoadSectionIsExpanded() throws InterruptedException 
    {
    	Assert.assertTrue(driver.findElement(By.id("days-timeline")).isDisplayed());
    }


}