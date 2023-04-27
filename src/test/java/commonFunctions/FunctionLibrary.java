package commonFunctions;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionLibrary
{
	public static WebDriver driver;
	public static Properties conpro;
	public static String Excepted="";
	public static String Actual="";
//Method For Lauching Webbrowser
	public static WebDriver startBrowser() throws Throwable
	
	{
		conpro =new Properties();
		conpro.load(new FileInputStream("./PropertyFile\\Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
		}else
			if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox"))
			{
				driver=new FirefoxDriver();
				driver.manage().deleteAllCookies();
				
			}else
			{
				System.out.println("Browser Not Matching");
			}
		return driver;
	}
	
	//method for launch url

	public static void openUrl(WebDriver driver)

	{

	driver.get(conpro.getProperty("Url"));

	}

	//method for wait for element

	public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String waitTime)

	{

	WebDriverWait myWait = new WebDriverWait(driver,Integer.parseInt(waitTime));

	if(LocatorType.equalsIgnoreCase("name"))

	{

	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));

	}

	else if(LocatorType.equalsIgnoreCase("xpath"))

	{

	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));

	}

	else if(LocatorType.equalsIgnoreCase("id"))

	{

	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));

	}

	}

	//method for textboxes

	public static void typeAction(WebDriver driver,String LocatorType,String LocatorValue,String TestData)

	{

	if(LocatorType.equalsIgnoreCase("id"))

	{

	driver.findElement(By.id(LocatorValue)).clear();

	driver.findElement(By.id(LocatorValue)).sendKeys(TestData);

	}

	else if(LocatorType.equalsIgnoreCase("xpath"))

	{

	driver.findElement(By.xpath(LocatorValue)).clear();

	driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);

	}

	else if(LocatorType.equalsIgnoreCase("name"))

	{

	driver.findElement(By.name(LocatorValue)).clear();

	driver.findElement(By.name(LocatorValue)).sendKeys(TestData);

	}

	}

	//method for bttons,radio,checkbox,links and images

	public static void clickAction(WebDriver driver,String LocatorType,String LocatorValue)

	{

	if(LocatorType.equalsIgnoreCase("xpath"))

	{

	driver.findElement(By.xpath(LocatorValue)).click();

	}

	else if(LocatorType.equalsIgnoreCase("name"))

	{

	driver.findElement(By.name(LocatorValue)).click();

	}

	else if(LocatorType.equalsIgnoreCase("id"))

	{

	driver.findElement(By.xpath(LocatorValue)).sendKeys(Keys.ENTER);

	}
	

	}
}
