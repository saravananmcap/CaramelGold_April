package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LinkedInProfileEditPage extends OpentapsWrappers {
	public LinkedInProfileEditPage(){
		if(!verifyTitle("Edit Profile | LinkedIn") ){
			Reporter.reportStep("Landed in Wrong Page.This is not Edit Profile page","FAIL");
		}
	}
	
	public LinkedInProfileEditPage clickAddSkill()
	{
		clickByXpath("(//button[text()='Add skill'])[2]");
		sleepForSec(3000);
		return this;
	}
	
	public LinkedInProfileEditPage enterAddSkill(String textVal)
	{
		enterById("edit-skills-add-ta", textVal);
		return this;
	}
	
	public LinkedInProfileEditPage clickAdd()
	{
		clickById("edit-skills-add-btn");
		sleepForSec(3000);
		return this;
	}
	
	public LinkedInProfileEditPage verifyError(String expectedVal)
	{
		verifyTextByXpath("//span[@id='skills-editSkillsForm-error']", expectedVal);
		return this;
	}

	public LinkedInProfileEditPage verifySuccess(String expectedVal)
	{
		//verifyTextContainsByXpath("//ol[@class='skills-list'][1]/following::span[contains(text(),'" + expectedVal + "')]", expectedVal);
		verifyTextContainsByXpath("//span[contains(text(),'"+expectedVal+"')]", expectedVal);
		return this;
	}
	
}
