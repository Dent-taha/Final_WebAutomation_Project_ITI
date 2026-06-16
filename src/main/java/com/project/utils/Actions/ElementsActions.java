package com.project.utils.Actions;

import com.project.utils.WaitManagement;
import com.project.utils.logs.logsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class ElementsActions {

    private final WebDriver driver;
    private final WaitManagement wait;


    public ElementsActions(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WaitManagement(driver);
    }
/*
    // click
   public  void click(By locator)
    {
        wait.fluentWait().until(d->{
            try {
               WebElement element=find(locator);
               scroll(locator);
                 element.click();
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                logsManager.error(e.getMessage());
                return false;
            }
        });

    }
    */
    public void click(By by)
    {

        WebElement element=wait.fluentWait()
                .until(ExpectedConditions.presenceOfElementLocated(by));

            scroll(by);

        wait.fluentWait()
                .until(ExpectedConditions.elementToBeClickable(by))
                .click();
    }



    //type
    public void Type(By  locator,String message)
    {
        wait.fluentWait().until(d->{
            try
            {
                WebElement element=find(locator);
                scroll(locator);
                element.clear();
                element.sendKeys(message);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    // type  v2
    public void type(By locator,String message)
    {
        WebElement element=wait.fluentWait().until(


                ExpectedConditions.visibilityOfElementLocated(locator)

        );
        scroll(locator);
        element.clear();
        element.sendKeys(message);
    }

    // get text
    public String getTXT(By  locator)
    {
        return wait.fluentWait().until(d-> {
            try {

                WebElement element = find(locator);
                scroll(locator);
                String msg = element.getText();
                if(msg.isEmpty())
                {
                    msg=element.getAttribute("textContent");
                }
                return msg!=null&& !msg.isBlank()
                        ?msg:null;
            }
            catch (Exception e){
                return  null;
            }
        });
    }

    public String getTxt(By by)
    {
        try{
            WebElement element = wait.fluentWait().until(
                    ExpectedConditions.visibilityOfElementLocated(by)
            );
            scroll(by);
            String msg =element.getText();
            if(msg.isEmpty())
            {
                msg=element.getAttribute("textContent");
            }
            return msg;
        } catch (Exception e) {
            logsManager.error("text  not found");
            throw e;

        }

    }

    //hovering

    public void hover(By  locator)
    {
        wait.fluentWait().until(driver1 -> {
            try
            {
                WebElement element=find(locator);
                scroll(locator);
                new Actions(driver1,Duration.ofSeconds(5)).moveToElement(element).perform();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        });
    }
    public void hover2(By locator)
    {
        WebElement element = wait.fluentWait()
                .until(ExpectedConditions.elementToBeClickable(locator));
        scroll(locator);

        new Actions(driver)
                .moveToElement(element)
                .pause(Duration.ofSeconds(4))
                .perform();
    }
    public  String  getValue(By locator)
    {
        wait.fluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        scroll(locator);
        return find(locator).getAttribute("value");

    }

    //find  element
    public WebElement find(By  locator)
    {
        return driver.findElement(locator);
    }
    public List<WebElement> findS(By  locator)
    {
        return driver.findElements(locator);
    }


    //scroll  by  java Executor
    public  void scroll(By locator)
    {
       ( (JavascriptExecutor)driver)
               .executeScript("""
                       arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"})""",find(locator));
    }
}
