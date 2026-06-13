package com.project.utils.Validation;

import com.project.utils.logs.logsManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class SoftAssert extends BaseAssertions{
    public SoftAssert(WebDriver driver) {
        super(driver);
    }

    public SoftAssert()
    {
        super();
    }
    private  static  org.testng.asserts.SoftAssert softAssert=new org.testng.asserts.SoftAssert();
    public static boolean used=false;

    @Override
    protected void AssertTrue(boolean condition, String...msg) {
        used=true;
        softAssert.assertTrue(condition,String.join(" ",msg));

    }

    @Override
    protected void assertFalse(boolean condition, String...msg) {
        used=true;
        softAssert.assertFalse(condition,String.join(" ",msg));

    }

    @Override
    public void assertEquals(String Actual, String Expected, String...msg) {
        used=true;
        softAssert.assertEquals(Actual,Expected,String.join(" ",msg));

    }

    public  static void assertAll(ITestResult result)
    {
        if(!used)return;
        try {
            softAssert.assertAll();
        } catch (Exception e) {
            result.setStatus(ITestResult.FAILURE);
            logsManager.error("Error in assert all");
            result.setThrowable(e);
        }
        finally {
            softAssert=new org.testng.asserts.SoftAssert();
        }
    }
}
