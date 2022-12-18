package com.edusoln.pom;

import java.time.Duration;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.edusoln.base.CommonMethods;
import com.edusoln.runner.TestNGRunner;

public class LoginPage extends CommonMethods{
	
	
	static Logger log=LogManager.getLogger(TestNGRunner.class);

@FindBy(name="username")
WebElement userid;
@FindBy(name="password")
WebElement password;
@FindBy(css="button[type='submit']")
WebElement login_btn;
@FindBy(className="oxd-brand-banner")
WebElement orangeHRM_logo;
 @FindBy(className="oxd-userdropdown-img") 
 WebElement profilepic;
 

public LoginPage() {
	PageFactory.initElements(driver, this);
}
public void orangeHRMLogin(Map<Object,Object>data, ExtentTest test) {
	waitForClick(userid);
	
	userid.sendKeys(data.get("User id").toString());
password.sendKeys(data.get("Password").toString());
login_btn.click();
//Assert.assertTrue(orangeHRM_logo.isDisplayed());
softassert.assertTrue(!orangeHRM_logo.isDisplayed());
waitForDisplay(profilepic);
hardwait(1000);
getScreenshot("LoginSucess");

}
}
