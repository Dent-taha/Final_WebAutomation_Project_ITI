package com.project.utils.Media;

import com.project.utils.ConstantPaths;
import com.project.utils.TimeManager;
import com.project.utils.logs.logsManager;
import com.project.utils.reports.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShot {

private final static String fileName= ConstantPaths.SCREENSHOTS.toString();


    // capture full  page
    public  static void  captureFullPage(WebDriver driver,String name)
    {
        try{
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dst = new File(fileName+File.separator + name +"-"+ TimeManager.getSimpleTimeStamp() + ".png");
            FileUtil.copyFile(src, dst);
            logsManager.info("successful capture");
            AllureAttachmentManager.attachScreenShots(name,dst.toPath());
        } catch (Exception e) {
            logsManager.error("capture failed");
            throw new RuntimeException(e);
        }
    }
// :todo
    //  add pics  to allure attachments;



    //  capture specific element

    public static void  captureElement(WebDriver driver,  By locator )
    {
        String name=driver.findElement(locator).getAccessibleName();
        try {
            File src=driver.findElement(locator).getScreenshotAs(OutputType.FILE);
            File dst=new File(fileName + name + TimeManager.getSimpleTimeStamp() + ".png");
            FileUtils.copyFile(src,dst);
            logsManager.info("Capture screenshot successfully for  ", name);
        } catch (Exception e) {
            logsManager.error("capture for  "  ,name," is failed  ");
        }
    }

}
