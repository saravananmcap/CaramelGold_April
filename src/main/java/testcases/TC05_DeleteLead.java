package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC05_DeleteLead extends OpentapsWrappers {
	
	@BeforeClass
	public void setData(){
		browserName="firefox";
		dataSheetName="TC005";
		testCaseName="DeleteLead";
		testDescription="Delete Lead(Positive)";
	}
	
	
	@Test(dataProvider="fetchData")
	public void login(String username,String password,String leadID){
		
		new LoginPage()
		.enterUsername(username)
		.enterPassword(password)
		.clickLoginButton()
		.clickCrmsfa()
		.clickLead()
		.clickFindLead()
		.enterLeadID(leadID)
		.clickFindLeadButton()
		.clickFirstLink()
		.clickDeleteButton()
		.clickFindLead()
		.enterLeadID(leadID)
		.clickFindLeadButton()
		.verifyNoRecord();
		//.clickLogout();
		
	}
	
	
	
	
	
	
	
	
	
	
}
