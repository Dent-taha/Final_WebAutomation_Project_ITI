package com.project.utils.Validation;

import com.project.utils.Actions.ElementsActions;
import com.project.utils.WaitManagement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseAssertions {

    protected  WebDriver driver;
    protected  WaitManagement wait;
    protected  ElementsActions elementActions;

    public BaseAssertions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitManagement(driver);
        this.elementActions = new ElementsActions(driver);
    }
    public BaseAssertions()
    {

    }
    protected abstract void AssertTrue(boolean condition,String...msg);
    protected abstract void assertFalse(boolean condition,String...msg);
    protected abstract void assertEquals(String Actual,String Expected,String...msg);


    //
    public void isElementDisplayed(By by) {
        boolean flag = wait.fluentWait().until(
                ExpectedConditions.visibilityOfElementLocated(by)
        ).isDisplayed();

        AssertTrue(flag, "Element is not visible");
    }

    public void isElementDisappeared(By locator) {
        wait.fluentWait().until(driver -> driver.findElements(locator).isEmpty());
    }
}
