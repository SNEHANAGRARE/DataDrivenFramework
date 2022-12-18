package com.edusoln.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
//For Normal Execution
public class CommonMethods {
public static WebDriver driver;
public static String homepath=System.getProperty("user.dir");
public static ExtentTest test;
public static SoftAssert softassert=new SoftAssert();
public static void browserLaunch(String browserName) {
	switch(browserName) {
	case "chrome":
		WebDriverManager.chromedriver().setup();
	//WebDriverManager.chromedriver().browserVersion("106.0.5249.119").setup();//for specific version
		driver=new ChromeDriver();
	break;
	
	case "edge":
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		break;
	case "firefox":
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		break;
		default:
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
	}
	
	driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
}



  public static void waitForClick(WebElement element) { 
	  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
  wait.until(ExpectedConditions.elementToBeClickable(element)); 
  }
  public static void waitForDisplay(WebElement element)
  { 
	  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
  wait.until(ExpectedConditions.visibilityOf(element)); 
  } 
  public static void hardwait(int time) 
  { 
	  try 
	  { 
		  Thread.sleep(time); 
		  }
	  catch(InterruptedException e)
  { 
	  e.printStackTrace(); 
	  }
 
 } 

  public static  String getScreenshot( String screenshotName) 
  {
	  TakesScreenshot ts=(TakesScreenshot)driver;
	  File src=ts.getScreenshotAs(OutputType.FILE);
  Date date=new Date(); 
  SimpleDateFormat format=new SimpleDateFormat("dd-M-yyyy hh"); 
  String datetime=format.format(date);
  System.out.println(datetime);
  File des=new File("C:\\Users\\PAWAN\\eclipse-workspace\\.metadata\\DataDrivenFramework\\src\\test\\resources\\Screenshot\\"+datetime+"\\"+screenshotName+".png");
  
  try { 
	  FileUtils.copyFile(src,des); 
	  }catch(IOException e) 
  {
  e.printStackTrace();
  }
  String destination=des.getAbsolutePath();
  return destination;
  
  } 
  public static void getScreenshot(WebElement element, String screenshotName)
  {
  
  File src=element.getScreenshotAs(OutputType.FILE);
  Date date=new Date();
  SimpleDateFormat format=new SimpleDateFormat("dd-M-yyyy hh");
  String datetime=format.format(date);
  System.out.println(datetime);
  File des=new File("C:\\Users\\PAWAN\\eclipse-workspace\\.metadata\\DataDrivenFramework\\src\\test\\resources\\Screenshot\\"+datetime+"\\"+screenshotName+".png"); 
  try 
  {
	  FileUtils.copyFile(src,des);
  }catch(IOException e) 
  { 
	  e.printStackTrace(); 
	  }
  
  }
  
  static int num=0; 
  public static int sentuniquenumber() 
  { 
	  num++;
	  return num; 
	  }
  
 public static int randomnumbergenerator() {
	int num=(int)Math.random();
	 
	 return num;
	 
 }




//this is for Normal Execution
public static ThreadLocal<WebDriver>driver1=new ThreadLocal<WebDriver>();
public static WebDriver getDriver() {
	return driver1.get();
}

public static void browserLaunchThreadlLocal(String browserName) {
	switch(browserName) {
	case "chrome":
		WebDriverManager.chromedriver().setup();
	//WebDriverManager.chromedriver().browserVersion("106.0.5249.119").setup();//for specific version
		driver1.set(new ChromeDriver());
	break;
	
	case "edge":
		WebDriverManager.edgedriver().setup();
		driver1.set(new EdgeDriver());
		break;
	case "firefox":
		WebDriverManager.firefoxdriver().setup();
		driver1.set(new FirefoxDriver());
		break;
		default:
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
	}
	getDriver().manage().window().maximize();
	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		

}

public String stringValue(Map<Object,Object>data,String Key) {
	return data.get(Key).toString();
}
public static String getDateTime() {
	Date date=new Date();
	SimpleDateFormat format=new SimpleDateFormat("dd-M-yyyy hh");
	String datetime=format.format(date);
	System.out.println(datetime);
	return datetime;
	}
public static String getDateTimeMins() {
	Date date=new Date();
	SimpleDateFormat format=new SimpleDateFormat("dd-M-yyyy hh_mm");
	String datetime=format.format(date);
	System.out.println(datetime);
	return datetime;
}
}






