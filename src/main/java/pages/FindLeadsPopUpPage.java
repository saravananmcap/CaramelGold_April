package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class FindLeadsPopUpPage extends OpentapsWrappers {
	public FindLeadsPopUpPage(){
		if(!verifyTitle("Find Leads") ){
			Reporter.reportStep("Landed in Wrong Page.This is not Find Lead popup page","FAIL");
		}
	}
	
	public FindLeadsPopUpPage switchToPopUp()
	{
		switchToLastWindow();
		return this;
	}
	
	public FindLeadsPopUpPage enterLeadID(String leadID)
	{
		enterByName("id", leadID);
		return this;
	}
	
	public FindLeadsPopUpPage clickFindLeadButton()
	{
		clickByXpath("//button[contains(text(),'Find Leads')]");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public FindLeadsPopUpPage verifyNoRecord()
	{
		verifyTextByXpath("//div[text()='No records to display']", "No records to display");
		return this;
	}
	
	public MergeLeadPage clickFirstLink()
	{
		clickByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a");
		sleepForSec(5000);
		switchToLastWindow();
		return new MergeLeadPage();
	}
}
