package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LinkedInSearchPage extends OpentapsWrappers {
	public LinkedInSearchPage(){
		System.out.println(driver.getTitle());
		if(!verifyTitle("Search | LinkedIn") ){
			Reporter.reportStep("Landed in Wrong Page.This is not LinkedIn Search page","FAIL");
		}
	}
	
	public LinkedInSearchPage enterCompanyName(String searchData)
	{
		enterById("advs-company", searchData);
		return this;
	}
	
	public LinkedInResultPage clickSearchButton()
	{
		clickByXpath("//input[@value='Search']");
		sleepForSec(3000);
		return new LinkedInResultPage();
	}
}
