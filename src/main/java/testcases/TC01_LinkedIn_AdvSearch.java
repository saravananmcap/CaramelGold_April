package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LinkedInLoginPage;
import wrappers.OpentapsWrappers;

public class TC01_LinkedIn_AdvSearch extends OpentapsWrappers {
	
	@BeforeClass
	public void setData(){
		browserName="chrome";
		dataSheetName="TC001_LinkedIn";
		testCaseName="AdvancedSearch";
		testDescription="Advanced Search(Positive)";
	}
	
	
	@Test(dataProvider="fetchData")
	public void login(String username,String password,String searchVal){
		
		new LinkedInLoginPage()
		.enterUserName(username)
		.enterPassword(password)
		.clickSignIn()
		.clickAdvancedSearch()
		.enterCompanyName(searchVal)
		.clickSearchButton()
		.getTotalResult();
	}
	
	
	
	
	
	
	
	
	
	
}
