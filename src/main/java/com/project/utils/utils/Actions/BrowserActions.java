package com.project.utils.utils.Actions;

import com.project.utils.WaitManagement;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private final WebDriver driver;
    private final WaitManagement wait;

//:todo
    // add  logs utility

    public BrowserActions(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WaitManagement(driver);
    }
    // refresh
    public void refresh()
    {
        driver.navigate().refresh();
    }
    //  navigate to  specific url
    public  void  navigate(String url)
    {
        driver.navigate().to(url);
    }

    //  get  current  url
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }



}
