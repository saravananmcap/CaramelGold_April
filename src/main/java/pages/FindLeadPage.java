package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class FindLeadPage extends OpentapsWrappers {
	public FindLeadPage(){
		if(!verifyTitle("Find Leads | opentaps CRM") ){
			Reporter.reportStep("Landed in Wrong Page.This is not Find Lead page","FAIL");
		}
	}
	
	public FindLeadPage enterLeadID(String leadID)
	{
		enterByName("id", leadID);
		return this;
	}
	
	public FindLeadPage clickFindLeadButton()
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
	
	public FindLeadPage verifyNoRecord()
	{
		verifyTextByXpath("//div[text()='No records to display']", "No records to display");
		return this;
	}
	
	public ViewLeadPage clickFirstLink()
	{
		clickByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a");
		return new ViewLeadPage();
	}
}
