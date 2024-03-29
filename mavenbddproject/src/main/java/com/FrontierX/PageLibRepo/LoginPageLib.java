package com.FrontierX.PageLibRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.FrontierX.PageObjRepo.LoginPageObj;

public class LoginPageLib extends LoginPageObj
{
	
	//	Constructor
	public LoginPageLib(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
		//
	}
	
	//	Creating Action Methods
	public HomePageLib login(String usernameValue, String passwordValue) 
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		String URL = driver.getCurrentUrl();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		username.sendKeys(usernameValue);
		switch (URL)
		{
		
		case "https://app.frontierxs.com/#/login" : // Serverless Stage
			System.out.println("Serverless Environment");
			password.sendKeys(passwordValue);
			loginButton.click();
			
			break;
			
		case "https://app.fourthfrontier.com/#/login" : // Existing Prod 
			System.out.println("Regular Environment");
			//btn btn-login
			driver.findElement(By.xpath("//*[contains(@class, 'btn btn-login')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
			password.sendKeys(passwordValue);
			loginButton.click();
			break;
		}
		
		//username.sendKeys(usernameValue);
		
		long startTime = System.currentTimeMillis();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("donut-inner")));
		long endTime = System.currentTimeMillis();
		System.out.println("Script waited for "+((endTime - startTime)/1000) % 60+" Seconds To load Training load circle for User "+usernameValue);
		return new HomePageLib(driver);
	}
	
	public void TryLogin(String usernameValue, String passwordValue) 
	{
		username.sendKeys(usernameValue);
		password.sendKeys(passwordValue);
		loginButton.click();
	}
	
	public void clickOnLogin()
	{
		loginButton.click();
	}
	
	public void VerifyRequiredFiledWarninigMsgs(List<String> Msgs)
	{
		
		
		Assert.assertEquals(getEmailWarningMsg(), Msgs.get(0));
		Assert.assertEquals(getPasswordWarningMsg(), Msgs.get(1));
		
	}
	
	public String getEmailWarningMsg()
	{
		try 
		{
		    return WarningMsg.get(0).getText();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			return driver.findElements(By.className("text-danger")).get(0).getText();
		}
	}
	
	public String getPasswordWarningMsg()
	{
		try 
		{
		    return WarningMsg.get(1).getText();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			return driver.findElements(By.className("text-danger")).get(1).getText();
		}
	}
	
	public void VerifyUserInfoPopUp(String expectedInfoTxt)
	{
		//Assert.assertEquals(NotificationInfoPopUp.getText(), expectedInfoTxt,"Found Expected text is "+expectedInfoTxt);
		Assert.assertTrue(NotificationInfoPopUp.getText().contains(expectedInfoTxt));
	}
	
	
	public void VerifyLoginPageElements()
	{
		
	//	Assert.assertTrue(loginButton.isDisplayed());
	//	Assert.assertTrue(NextBtn.isDisplayed());
		Assert.assertTrue(username.isDisplayed());
		
	}
	
	public void ClickNextBtn()
	{
		NextBtn.click();
	}
	
}
