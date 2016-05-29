package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC06_MergeLead extends OpentapsWrappers {
	
	@BeforeClass
	public void setData(){
		browserName="firefox";
		dataSheetName="TC006";
		testCaseName="MergeLead";
		testDescription="Merge Lead(Positive)";
	}
	
	
	@Test(dataProvider="fetchData")
	public void login(String username,String password,String fromLeadId, String toLeadID){
		
		new LoginPage()
		.enterUsername(username)
		.enterPassword(password)
		.clickLoginButton()
		.clickCrmsfa()
		.clickLead()
		.clickMergeLead()
		.clickFromLeadImg()
		.switchToPopUp()
		.enterLeadID(fromLeadId)
		.clickFindLeadButton()
		.clickFirstLink()
		.clickToLeadImg()
		.switchToPopUp()
		.enterLeadID(toLeadID)
		.clickFindLeadButton()
		.clickFirstLink()
		.clickMergeLeadButton()
		.clickFindLead()
		.enterLeadID(fromLeadId)
		.clickFindLeadButton()
		.verifyNoRecord();
		//.clickLogout();
		
	}
	
	
	
	
	
	
	
	
	
	
}
