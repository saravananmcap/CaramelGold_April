package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class CreateLeadPage extends OpentapsWrappers {
	public CreateLeadPage(){
		if(!verifyTitle("Create Lead | opentaps CRM") ){
			Reporter.reportStep("Landed in Wrong Page.This is not Create Lead page","FAIL");
		}
	}
	
	public CreateLeadPage enterCompanyName(String companyName)
	{
		enterById("createLeadForm_companyName", companyName);
		return this;
	}
	
	public CreateLeadPage enterFirstName(String firstName)
	{
		enterById("createLeadForm_firstName", firstName);
		return this;
	}
	
	public CreateLeadPage enterLastName(String lastName)
	{
		enterById("createLeadForm_lastName", lastName);
		return this;
	}
	
	public ViewLeadPage clickCreateLeadButton()
	{
		clickByXpath("//input[@name='submitButton']");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ViewLeadPage();
	}
}
