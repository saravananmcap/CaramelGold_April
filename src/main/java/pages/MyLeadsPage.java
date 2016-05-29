package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class MyLeadsPage extends OpentapsWrappers{
	public MyLeadsPage(){
		if(!verifyTitle("My Leads | opentaps CRM") ){
			Reporter.reportStep("Landed in Wrong Page.This is not Leads Homepage","FAIL");
		}
	}
	
	public FindLeadPage clickFindLead()
	{
		clickByXpath("//a[text()='Find Leads']");
		return new FindLeadPage();
	}
	
	public CreateLeadPage clickCreateLead()
	{
		clickByXpath("//a[text()='Create Lead']");
		return new CreateLeadPage();
	}
	
	public MergeLeadPage clickMergeLead()
	{
		clickByXpath("//a[text()='Merge Leads']");
		return new MergeLeadPage();
	}
}
