package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC03_CreateLead extends OpentapsWrappers {
	
	@BeforeClass
	public void setData(){
		browserName="firefox";
		dataSheetName="TC003";
		testCaseName="CreateLead";
		testDescription="Create Lead(Positive)";
	}
	
	
	@Test(dataProvider="fetchData")
	public void login(String username,String password,String companyName, String fName, String lName){
		
		new LoginPage()
		.enterUsername(username)
		.enterPassword(password)
		.clickLoginButton()
		.clickCrmsfa()
		.clickLead()
		.clickCreateLead()
		.enterCompanyName(companyName)
		.enterFirstName(fName)
		.enterLastName(lName)
		.clickCreateLeadButton()
		.verifyLeadId(companyName);
		//.clickLogout();
		
	}
	
	
	
	
	
	
	
	
	
	
}
