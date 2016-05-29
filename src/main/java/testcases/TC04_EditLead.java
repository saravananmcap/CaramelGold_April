package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC04_EditLead extends OpentapsWrappers {
	
	@BeforeClass
	public void setData(){
		browserName="firefox";
		dataSheetName="TC004";
		testCaseName="EditLead";
		testDescription="Edit Lead(Positive)";
	}
	
	
	@Test(dataProvider="fetchData")
	public void login(String username,String password,String leadID, String dataSource, String marketingCampaign){
		
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
		.clickEditButton()
		.selectDataSource(dataSource)
		.clickAddSource()
		.verifyDataSource(dataSource)
		.selectMarketCampaign(marketingCampaign)
		.clickAddCampaign()
		.verifyCampaign(marketingCampaign);
		//.clickLogout();
		
	}
	
	
	
	
	
	
	
	
	
	
}
