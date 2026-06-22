package com.project.drivers;

import com.project.utils.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Objects;

public class EdgeFactory  extends AbstractDriver{

    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(edgeOptions());
    }

    //  driver  options
    public EdgeOptions edgeOptions()
    {
        EdgeOptions edgeOptions=  new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-popup-blocking");
        edgeOptions.addArguments("--disable-notifications");
        if(Objects.equals(PropertyReader.getProperty("executionType"), "LocalHeadless"))
        {
            edgeOptions.addArguments("--headless=new");
            edgeOptions.addArguments("--window-size=1920,1080");
            edgeOptions.addArguments("--disable-dev-shm-usage");
            edgeOptions.addArguments("--no-sandbox");
        }

        // منع infobars
        edgeOptions.addArguments("--disable-infobars");

        // تعطيل بعض features المزعجة
        edgeOptions.addArguments("--disable-features=msEdgeCopilot,EdgeShoppingAssistant");
        return edgeOptions;
    }
}
