package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LinkedInHomePage extends OpentapsWrappers {
	public LinkedInHomePage(){
		if(!verifyTitle("Welcome! | LinkedIn") ){
			Reporter.reportStep("Landed in Wrong Page.This is not LinkedIn Home page","FAIL");
		}
	}
	
	public LinkedInSearchPage clickAdvancedSearch()
	{
		clickById("advanced-search");
		sleepForSec(5000);
		return new LinkedInSearchPage();
	}
	
	public LinkedInHomePage mouseOverProfileMenu()
	{
		mouseOverByXpath("(//a[contains(text(),'Profile')])[1]");
		return this;
	}
	
	public LinkedInProfileEditPage clickEditProfile()
	{
		clickByXpath("(//a[contains(text(),'Edit Profile')])[1]");
		return new LinkedInProfileEditPage();
	}
}
