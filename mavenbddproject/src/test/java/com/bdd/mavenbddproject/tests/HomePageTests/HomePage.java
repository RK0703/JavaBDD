package com.bdd.mavenbddproject.tests.HomePageTests;
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
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class HomePage extends Settings


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
		 try
		 {
			 this.driver = DecideEnvironment(env);
			 loginObj = new LoginPageLib(driver);
			 
			 
			 if(env == "serverless")
			 {
				 homeObj = loginObj.login("ravikiran@fourthfrontier.com",FxUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Ravikiran : Serverless Production user");
			 }
			 
			 else
			 {   // Regular Prod User
				 homeObj = loginObj.login("quality+automation@fourthfrontier.com",FxUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality user : Regular Prod user");
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
    	 driver.quit();
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
	
 
    @Given("User is on Frontier X Web Home page {string}")
    public void LaunchWebPage(String string)
    {
    	
    	Initialize(string);
    }
    
    @When("User enters activity in seach text box")
    public void SearchForActivity() throws InterruptedException
    {
    	//String Activity = "Run";
		homeObj.searchWorkout(Activity);
    }
    
    @Then("User should be able to see searched activity")
    public void VerifySearchedActivityIsDisplayed() throws InterruptedException 
    {
    	
		//homeObj.searchWorkout(Activity);
		homeObj.ReadSearchedActivity(Activity);
 
    }
   

 
}