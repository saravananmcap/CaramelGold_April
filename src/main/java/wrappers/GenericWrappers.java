package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import utils.Reporter;

public class GenericWrappers {

	protected static RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl,primaryWindowHandle,sHubUrl,sHubPort;

	public GenericWrappers() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Babu - TestLeaf
	 * @param url - The url with http or https
	 * 
	 */
	public boolean invokeApp(String browser) {
		boolean bReturn = false;
		try {

			if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}else
				driver = new FirefoxDriver();

			driver.get(sUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			primaryWindowHandle = driver.getWindowHandle();		
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS");
			bReturn = true;

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
		return bReturn;
	}

	public boolean invokeAppRemote(String browser) {
		boolean bReturn = false;
		try {
			boolean remote = true;
			if (remote) {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);

			driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			}
			if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}else
				driver = new FirefoxDriver();
			
			driver.get(sUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			

			primaryWindowHandle = driver.getWindowHandle();		
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS");
			bReturn = true;

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Babu - TestLeaf
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterById(String idValue, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Babu - TestLeaf
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterByName(String nameValue, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.name(nameValue)).clear();
			driver.findElement(By.name(nameValue)).sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+nameValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+nameValue, "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param xpathValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Babu - TestLeaf
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterByXPath(String xpathValue, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.xpath(xpathValue)).clear();
			driver.findElement(By.xpath(xpathValue)).sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+xpathValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+xpathValue, "FAIL");
		}
		return bReturn;
	}


	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS");
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS");

		}catch (Exception e) {
			Reporter.reportStep("The title did not match", "FAIL");
		}

		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().equalsIgnoreCase(text)){
			Reporter.reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
		}


		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextContainsByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().contains(text)){
			Reporter.reportStep("The text: "+sText+" contains the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not contain the value :"+text, "FAIL");
		}


		return bReturn;
	}

	/**
	 * This method will close all the browsers
	 * @author Babu - TestLeaf
	 */
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Reporter.reportStep("The browser:"+driver.getCapabilities().getBrowserName()+" could not be closed.", "FAIL");
		}

	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickById(String id) {
		boolean bReturn = false;
		try{
			driver.findElement(By.id(id)).click();
			Reporter.reportStep("The element with id: "+id+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with id: "+id+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByClassName(String classVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.className(classVal)).click();
			Reporter.reportStep("The element with class Name: "+classVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with class Name: "+classVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByName(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.name(name)).click();
			Reporter.reportStep("The element with name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using link name as locator
	 * @param name  The link name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByLink(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.linkText(name)).click();
			Reporter.reportStep("The element with link name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with link name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.xpath(xpathVal)).click();
			Reporter.reportStep("The element : "+xpathVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.xpath(xpathVal))).build().perform();
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using link name as locator
	 * @param xpathVal  The link name (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByLinkText(String linkName) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.linkText(linkName))).build().perform();
			Reporter.reportStep("The mouse over by link : "+linkName+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by link : "+linkName+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	public String getTextByXpath(String xpathVal){
		String bReturn = "";
		try{
			return driver.findElement(By.xpath(xpathVal)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL");
		}
		return bReturn; 
	}

	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Babu - TestLeaf
	 */
	public boolean selectById(String id, String value) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.id(id))).selectByVisibleText(value);;
			Reporter.reportStep("The element with id: "+id+" is selected with value :"+value, "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The value: "+value+" could not be selected.", "FAIL");
		}
		return bReturn;
	}

	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));

	}

	/**
	 * Method Name			:verifyTextById
	 * @param cssValue		:locator value
	 * @param expectedValue :value expected in the WebElement
	 */
	public void verifyTextById(String id, String expectedValue)
	{
		try
		{
			String actualValue = "";

			actualValue = driver.findElementById(id).getText();

			//Compare the actual with expected
			if(actualValue.equalsIgnoreCase(expectedValue))
				Reporter.reportStep("WebElement Text is as expected. Actual Value is: " + actualValue, "PASS");
			else
				Reporter.reportStep("WebElement Text is not as expected:'" + expectedValue + "'. Actual Value is: " + actualValue, "FAIL");

		}
		catch(NoSuchElementException e)
		{
			Reporter.reportStep("WebElement Text is not as available with ID" + id, "FAIL");
		}
		finally
		{
			//calling function to take screenshot
			//	takeScreenShot();
		}			
	}
	
	/*
	 * Method Name			:verifyTextById
	 * @param cssValue		:locator value
	 * @param expectedValue :value expected in the WebElement
	 */
	public void verifyTextContainsById(String id, String expectedValue)
	{
		try
		{
			String actualValue = "";

			actualValue = driver.findElementById(id).getText();

			//Compare the actual with expected
			if(actualValue.contains(expectedValue))
				Reporter.reportStep("WebElement Text is as expected. Actual Value is: " + actualValue, "PASS");
			else
				Reporter.reportStep("WebElement Text is not as expected:'" + expectedValue + "'. Actual Value is: " + actualValue, "FAIL");

		}
		catch(NoSuchElementException e)
		{
			Reporter.reportStep("WebElement Text is not as available with ID" + id, "FAIL");
		}
		finally
		{
			//calling function to take screenshot
			//	takeScreenShot();
		}			
	}

	/**
	 * Method Name		:linkClickByClass
	 * @param className	:locator value
	 */
	public void linkClickByClass(String className)
	{
		try
		{
			driver.findElementByClassName(className).click();
			//Make driver to sleep
			sleepForSec(3000);

			Reporter.reportStep("WebElement Text is located with class" + className, "PASS");
			System.out.println("Element was located and clicked");
		}
		catch(NoSuchElementException e)
		{
			Reporter.reportStep("WebElement Text is not located with class" + className, "FAIL");
		}
		finally
		{
			//calling function to take screenshot
			//takeScreenShot();
		}		
	}


	/**
	 * Method Name : sleepForSec
	 * @param msecs : Number of seconds to be wait
	 */
	public void sleepForSec(long msecs)
	{
		try
		{
			Thread.sleep(msecs);
		}
		catch (InterruptedException e)
		{
			Reporter.reportStep("Exception raised on Thread.Sleep method", "FAIL");
		}
	}

	/**
	 * Method Name	: getCurrentWindowHandle
	 * @return
	 */
	public String getCurrentWindowHandle()
	{
		String windowHandle = "";
		try
		{
			windowHandle = driver.getWindowHandle();
		}
		catch(Exception e)
		{
			Reporter.reportStep("Exception throws while getting WindowHandle of Current Window", "FAIL");
		}
		finally
		{
			//takeScreenShot();
		}
		return windowHandle;
	}

	/**
	 * Method Name: switchToLastWindow
	 * Method to make driver to switch back to main page
	 */
	public void switchToLastWindow()
	{
		try
		{
			//Switching the window
			Set<String> allWindow = driver.getWindowHandles();

			for (String oWnd : allWindow) {
				driver.switchTo().window(oWnd);
			}
		}
		catch(Exception e)
		{
			Reporter.reportStep("Switching to last window throws exception", "FAIL");
		}
		finally
		{
			//takeScreenShot();
		}
	}

	/**
	 * Method Name: switchToSpecificWindow
	 * Method to make driver to switch back to main page
	 */
	public void switchToSpecificWindow(String windowHandle)
	{
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			System.out.println("switch to specific window");
			for (String oWnd : allWindow)
			{
				if(oWnd.equals(windowHandle))
				{
					driver.switchTo().window(oWnd);
					break;
				}
			}
			System.out.println("Switched to window : " + windowHandle);
		}
		catch(Exception e)
		{
			Reporter.reportStep("Switching to Specific window throws exception", "FAIL");
		}
		finally
		{
			//takeScreenShot();
		}
	}

	/**
	 * Method Name		:alertHandle
	 * @param alertType	:PROMPT/Alert
	 * @param value		:Value to be inputted in Alert box
	 */
	public void alertHandle(String alertType, String value)
	{
		try
		{
			System.out.println("Enter into alert handle method");
			//Swtiching to alert
			Alert alert_box = driver.switchTo().alert();

			if(alertType.equalsIgnoreCase("PROMPT"))
			{
				//Inputing the prompt
				alert_box.sendKeys(value);
			}

			//Clicking OK button on prompt
			alert_box.accept();

			Reporter.reportStep("Alert is handled successfully", "PASS");
		}
		catch(UnhandledAlertException e)
		{
			Reporter.reportStep("No Alert to Handle", "FAIL");
		}
		catch(Exception e)
		{
			Reporter.reportStep("Exception in handling alert", "FAIL");
		}
		finally
		{
			//calling method to take screenshot
			//takeScreenShot();
		}
	}
	
	/*
	 * Method Name : sleepForSec
	 * @param msecs : Number of seconds to be wait
	 */
	public void sleepForLongSec(String msecs)
	{
		long msec = Long.parseLong(msecs);
		try
		{
			Thread.sleep(msec);
		}
		catch (InterruptedException e)
		{
			Reporter.reportStep("Exception raised on Thread.Sleep method", "FAIL");
		}
	}

}

