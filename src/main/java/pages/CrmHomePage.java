package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class CrmHomePage extends OpentapsWrappers {
	public CrmHomePage(){
		if(!verifyTitle("My Home | opentaps CRM") ){
			Reporter.reportStep("Landed in Wrong Page.This is not CRM HomePage","FAIL");
		}
	}
	
	public MyLeadsPage clickLead()
	{
		clickByXpath("//a[text()='Leads']");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new MyLeadsPage();
	}
}
