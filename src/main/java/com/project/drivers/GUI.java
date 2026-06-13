package com.project.drivers;

import com.project.utils.Actions.*;
import com.project.utils.Validation.HardAssert;
import com.project.utils.Validation.SoftAssert;
import com.project.utils.dataReader.PropertyReader;
import com.project.utils.logs.logsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.util.Objects;

public class GUI {

private String browser= PropertyReader.getProperty("browserType");
private ThreadLocal<WebDriver> driverThreadLocal=new ThreadLocal<>();


public GUI()
{
    logsManager.info(" browser type  is", browser);
Browsers browserType=Browsers.valueOf(Objects.requireNonNull(browser).toUpperCase());
AbstractDriver abstractDriver=browserType.getDriverFactory();
WebDriver driver= ThreadGuard.protect(abstractDriver.createDriver());
driverThreadLocal.set(driver);
}

public   WebDriver getDriver()
{
    return driverThreadLocal.get();
}

public void Quit()
{
    driverThreadLocal.get().quit();
}

//

    public ElementsActions elementsActions(){return  new ElementsActions(getDriver());}
    public BrowserActions browserActions(){return  new BrowserActions(getDriver());}
    public AlertActions alertActions(){return  new AlertActions(getDriver());}
    public FrameActions frameActions(){return  new FrameActions(getDriver());}
    public HardAssert verify(){return new HardAssert(getDriver());}
    public SoftAssert validate(){return  new SoftAssert(getDriver());}
    public DropDownActions dropDownActions(){return new DropDownActions(getDriver());}

}
