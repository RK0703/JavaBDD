package com.FrontierX.PageObjRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import  com.FrontierX.BaseSettings.BasePage;

public class LoginPageObj extends BasePage
{

	//	Creating WebElements
	@FindBy(id = "email")
	protected WebElement username ;
	
	@FindBy(id = "password")
	protected WebElement password ;
	
	@FindBy(xpath = "//input[@value='Login']")
	protected WebElement loginButton ;
	
	protected WebElement NextBtn = driver.findElement(By.xpath("//*[contains(@class, 'btn btn-login')]"));
	
	@FindBy (className = "mini-toastr-notification__message")
	protected WebElement NotificationInfoPopUp;
	
	protected List<WebElement> WarningMsg = driver.findElements(By.className("text-danger"));	
	
	//	Constructor
	public LoginPageObj(WebDriver driver) 
	{
		//PageFactory.initElements(driver, this);
		super(driver);
	}
	
	//	Creating Action Methods
	public HomePageObj login(String usernameValue, String passwordValue) {
		username.sendKeys(usernameValue);
		password.sendKeys(passwordValue);
		loginButton.click();
		return new HomePageObj(driver);
	}
	
	
	
}
