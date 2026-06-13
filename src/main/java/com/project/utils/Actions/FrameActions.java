package com.project.utils.Actions;

import com.project.utils.WaitManagement;
import org.openqa.selenium.WebDriver;

public class FrameActions {

    private final WebDriver driver;
    private final WaitManagement wait;

    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitManagement(driver);
    }

    public void switchToFrameByName(String name) {
        wait.fluentWait().until(driver1 -> {
            try {
                driver1.switchTo().frame(name);
                return true;
            } catch (Exception e) {
                return false;

            }
        });

    }

    public void returnToDefaultFrame() {
        wait.fluentWait().until(d -> {
            try {
                driver.switchTo().defaultContent();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}
