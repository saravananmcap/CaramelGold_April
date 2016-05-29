package wrappers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import pages.LinkedInResultPage;
import utils.DataInputProvider;
import utils.Reporter;

public class OpentapsWrappers extends GenericWrappers {
	
	protected String browserName;
	protected String dataSheetName;
	protected static String testCaseName;
	protected static String testDescription;
	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException{
		Reporter.startResult();
		loadObjects();
	}

	@BeforeTest
	public void beforeTest(){
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		Reporter.startTestCase();
		//invokeAppRemote(browserName);
		invokeApp(browserName);
	}
		
	@AfterSuite
	public void afterSuite(){
		Reporter.endResult();
	}

	@AfterTest
	public void afterTest(){
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(){
		quitBrowser();
	}
	
	@DataProvider(name="fetchData")
	public Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}	
	
	public void getTextByXpathandReplace(String xpathVal)
	{
		String val = getTextByXpath(xpathVal);
		String val1 = val.replace(",", "");
		
		Reporter.reportStep("Number of Search result returned is " + val1, "PASS");
	}
	
}
