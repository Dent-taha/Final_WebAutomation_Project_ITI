package com.project.utils.Actions;

import com.project.utils.WaitManagement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDownActions {

    private final WaitManagement wait;
    private final ElementsActions action;



    public DropDownActions(WebDriver driver)
    {
        this.wait=new WaitManagement(driver);
        this.action=new ElementsActions(driver);
    }

    public  void selectBox(By locator, int index)
    {
        wait.fluentWait().until(driver1 -> {
            try {
                action.scroll(locator);
                new Select(driver1.findElement(locator)).selectByIndex(index);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

    }
    public  void selectBox(By locator, String name)
    {
        wait.fluentWait().until(driver1 -> {
            try {
                action.scroll(locator);
                new Select(driver1.findElement(locator)).selectByValue(name);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

    }
}
