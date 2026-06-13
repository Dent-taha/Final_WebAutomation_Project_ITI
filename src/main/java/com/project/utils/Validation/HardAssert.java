package com.project.utils.Validation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HardAssert extends BaseAssertions {
    public HardAssert(WebDriver driver) {
        super(driver);
    }

    public HardAssert()
    {
super();
    }

    @Override
    public void AssertTrue(boolean condition, String...msg) {
        Assert.assertTrue(condition,String.join(" ",msg));

    }

    @Override
    public void assertFalse(boolean condition, String...msg) {
        Assert.assertFalse(condition,String.join(" ",msg));

    }

    @Override
    public void assertEquals(String Actual, String Expected, String...msg) {
        Assert.assertEquals(Actual,Expected,String.join("",msg));

    }


}
