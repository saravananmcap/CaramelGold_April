package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LinkedInResultPage extends OpentapsWrappers {
	public LinkedInResultPage(){
		if(!verifyTitle("Search | LinkedIn") ){
			Reporter.reportStep("Landed in Wrong Page.This is not LinkedIn Search Result page","FAIL");
		}
	}
	
	public LinkedInResultPage getTotalResult()
	{
		String val = getTextByXpath("//div[@id='results_count']/div/p/strong");
		System.out.println("total result" + val);
		String val1 = val.replace(",", "");
		System.out.println("new value" + val1);
	
		Reporter.reportStep("Number of Search result returned is " + val1, "PASS");
		return this;
	}
}
