package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class EditLeadPage extends OpentapsWrappers {
	public EditLeadPage(){
		if(!verifyTitle("opentaps CRM") ){
			Reporter.reportStep("Landed in Wrong Page.This is not Edit Lead page","FAIL");
		}
	}
	
	public EditLeadPage selectDataSource(String lstValue)
	{
		selectById("addDataSourceForm_dataSourceId", lstValue);
		return this;
	}
	
	public EditLeadPage selectMarketCampaign(String lstValue)
	{
		selectById("addMarketingCampaignForm_marketingCampaignId", lstValue);
		return this;
	}
	
	public EditLeadPage clickAddSource()
	{
		clickByXpath("(//input[@class='smallSubmit' and @value='Add'])[1]");
		return this;
	}
	
	public EditLeadPage clickAddCampaign()
	{
		clickByXpath("(//input[@class='smallSubmit' and @value = 'Add'])[2]");
		return this;
	}
	
	public EditLeadPage verifyDataSource(String data)
	{
		verifyTextById("listDataSources_dataSourceId_o_0_sp", data);
		return this;
	}
	
	public EditLeadPage verifyCampaign(String data)
	{
		verifyTextContainsByXpath("(//table[@class='crmsfaListTable'])[2]//tr[2]//td//a", data);
		return this;
	}
}
