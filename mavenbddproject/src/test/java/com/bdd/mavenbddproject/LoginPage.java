package com.bdd.mavenbddproject;
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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class LoginPage extends Settings


{
    private WebDriver driver;
    public final static int TIMEOUT = 5;
 
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 String ClassName = this.getClass().getSimpleName().toString();
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
		 driver.quit();
	 }
	
 
    @Given("User is on Frontier X Web Login page {string}")
    public void LaunchWebPage(String string)
    {
    	
    	Initialize(string);
        // Write code here that turns the phrase above into concrete actions
    	// driver.get(string);
      //  throw new io.cucumber.java.PendingException();
    }
    
    @When("User enters username password")
    public void EnterCredentials()
    {
    	 
    }
    
    @Then("User should be able to login sucessfully and new page open")
    public void VerifyHomePageVersion() 
    {
    	String version = homeObj.getVersionInfo();
        
        Assert.assertEquals(version, "v1.6.8");
 
    }
   

 
}