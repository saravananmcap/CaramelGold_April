package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class ViewLeadPage extends OpentapsWrappers {
	public ViewLeadPage(){
		if(!verifyTitle("View Lead | opentaps CRM") ){
			Reporter.reportStep("Landed in Wrong Page.This is not View Lead page","FAIL");
		}
	}
	
	public ViewLeadPage verifyLeadId(String verifyText)
	{
		verifyTextContainsByXpath("//span[@id='viewLead_companyName_sp']", verifyText);
		return this;
	}
	
	public LoginPage clickLogout()
	{
		clickByXpath("(//a[@class='linktext'])[3]");
		return new LoginPage();
	}
	
	public EditLeadPage clickEditButton()
	{
		clickByXpath("//a[contains(text(),'Edit')]");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new EditLeadPage();
	}
	
	public MyLeadsPage clickDeleteButton()
	{
		linkClickByClass("subMenuButtonDangerous");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new MyLeadsPage();
	}
	
	public FindLeadPage clickFindLead()
	{
		clickByXpath("//a[text()='Find Leads']");
		return new FindLeadPage();
	}
}
