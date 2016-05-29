package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LinkedInLoginPage extends OpentapsWrappers  {
	public LinkedInLoginPage(){
		if(!verifyTitle("World’s Largest Professional Network | LinkedIn") ){
			Reporter.reportStep("Landed in Wrong Page.This is not LinkedIn Login page","FAIL");
		}
	}
	
	public LinkedInLoginPage enterUserName(String userName)
	{
		enterById("login-email", userName);
		return this;
	}
	
	public LinkedInLoginPage enterPassword(String password)
	{
		enterById("login-password", password);
		return this;
	}
	
	public LinkedInHomePage clickSignIn()
	{
		clickByXpath("//input[@name='submit']");
		return new LinkedInHomePage();
	}
}
