package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class MergeLeadPage extends OpentapsWrappers {
	public MergeLeadPage(){
		if(!verifyTitle("Merge Leads | opentaps CRM") ){
			Reporter.reportStep("Landed in Wrong Page.This is not Merge Lead page","FAIL");
		}
	}
	
	String currentWind = getCurrentWindowHandle();
	
	public FindLeadsPopUpPage clickFromLeadImg()
	{
		clickByXpath("//span[contains(text(), 'From Lead')]/following::a");
		sleepForSec(5000);
		switchToLastWindow();
		return new FindLeadsPopUpPage();
	}
	
	public FindLeadsPopUpPage clickToLeadImg()
	{
		clickByXpath("//span[contains(text(), 'To Lead')]/following::a");
		sleepForSec(5000);
		switchToLastWindow();
		return new FindLeadsPopUpPage();
	}

	public ViewLeadPage clickMergeLeadButton()
	{
		linkClickByClass("buttonDangerous");
		sleepForSec(3000);
		alertHandle("alert","");
		sleepForSec(5000);
		return new ViewLeadPage();
	}
}
