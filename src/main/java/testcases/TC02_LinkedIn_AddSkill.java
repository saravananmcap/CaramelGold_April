package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LinkedInLoginPage;
import wrappers.OpentapsWrappers;

public class TC02_LinkedIn_AddSkill extends OpentapsWrappers {
	
	@BeforeClass
	public void setData(){
		browserName="chrome";
		dataSheetName="TC002_LinkedIn_AddSkill";
		testCaseName="AddSkill";
		testDescription="Add Skill(Positive)";
	}
	
	
	@Test(dataProvider="fetchData")
	public void login(String username,String password, String skillDuplicate, String errorMsg, String skillOriginal){
		
		new LinkedInLoginPage()
		.enterUserName(username)
		.enterPassword(password)
		.clickSignIn()
		.mouseOverProfileMenu()
		.clickEditProfile()
		.clickAddSkill()
		.enterAddSkill(skillDuplicate)
		.clickAdd()
		.verifyError(errorMsg)
		.enterAddSkill(skillOriginal)
		.clickAdd()
		.verifySuccess(skillOriginal);
		
	}
	
	
	
	
	
	
	
	
	
	
}
