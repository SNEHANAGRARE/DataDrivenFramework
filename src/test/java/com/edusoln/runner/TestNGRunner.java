package com.edusoln.runner;


import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.edusoln.Utilities.ExcelReader;
import com.edusoln.Utilities.ExtentReporter;
import com.edusoln.Utilities.PropertyReader;
import com.edusoln.base.CommonMethods;
import com.edusoln.base.Constants;
import com.edusoln.pom.LoginPage;
import com.edusoln.pom.PIMPage;

public class TestNGRunner extends CommonMethods{
LoginPage login;
PIMPage pim;
static Logger log=LogManager.getLogger(TestNGRunner.class);

ExtentReports report;
@BeforeTest
public void beforeTest() {
	report=ExtentReporter.initReport();
log.info("Execution has started");
}


@BeforeMethod
public void browserSetUp() {
	browserLaunch(PropertyReader.getPropValue(Constants.BROWSER));
	driver.get(PropertyReader.getPropValue(Constants.URL));
	log.debug(PropertyReader.getPropValue(Constants.BROWSER)+"has been launched");

}

@DataProvider(name="dp")
public Object[][] dataSupplier() 
{
	Object[][] finaldata = ExcelReader.readExcel();
	
	return finaldata;
	
}



@Test(dataProvider="dp",priority=0)
public void testcase(Map<Object,Object>data) {
	log.info("test has started");
	softassert=new SoftAssert();
	test=report.createTest(stringValue(data,"TestCaseObjective"));
	if(stringValue(data,"Execute").equals("Yes")) {
	login=new LoginPage();
	login.orangeHRMLogin(data,test);
pim=new PIMPage();
pim.addemployee(data,test);
log.warn("soft assertion failured observed");
softassert.assertAll();
	
	}
	else {
		test.log(Status.SKIP, "test has been skipped");
		log.error("test has been started");
		throw new SkipException("Test case is not run");
		}
	}
	/*
	 * @Test(dataProvider="dp",priority=1,enabled=false) public void
	 * addemployee(Map<Object,Object>data) { login=new LoginPage();
	 * login.orangeHRMLogin(data); pim=new PIMPage(); pim.addemployee(data);
	 */

	/* } */

@AfterMethod
public void tearDown() {
	driver.quit();
}
@AfterTest
public void afterTest() {
	
	
	report.flush();
	//softassert.assertAll();

}
@AfterSuite
public void Suite() {
	System.out.println("Running from after suite");
	report.flush();
}
}
