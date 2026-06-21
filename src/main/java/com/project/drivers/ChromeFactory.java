package com.project.drivers;

import com.project.utils.ConstantPaths;
import com.project.utils.dataReader.PropertyReader;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChromeFactory  extends AbstractDriver{


    @Override
    public WebDriver createDriver() {
        return new ChromeDriver(chromeOptions());
    }
    public ChromeOptions chromeOptions()
    {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        if(Objects.equals(PropertyReader.getProperty("executionType"), "LocalHeadless"))
        {
            options.addArguments("--headless=new");
        }

        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(
                UnexpectedAlertBehaviour.IGNORE);

        Map<String,Object> prefs = new HashMap<>();

        prefs.put("profile.default_content_setting.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put(
                "download.default_directory",
                ConstantPaths.RESOURCES.toString()
        );

        options.setExperimentalOption("prefs", prefs);

        return options;
    }
}
