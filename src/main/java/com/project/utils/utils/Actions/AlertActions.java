package com.project.utils.utils.Actions;

import com.project.utils.WaitManagement;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;

public class AlertActions {

    private final WebDriver driver;
    private final WaitManagement wait;


    public AlertActions(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WaitManagement(driver);
    }
    // accept alert
    public void acceptAlert()
    {
        wait.fluentWait().until(d->{
            try
            {
                d.switchTo().alert().accept();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }

        });
    }
    // dismiss  alert
    public void dismissAlert()
    {
        wait.fluentWait().until(d->{
            try
            {
                d.switchTo().alert().dismiss();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        });
    }

    //  get  txt  from  alert
    public String getAlertText()
    {
       return wait.fluentWait().until(d->{
            try
            {
                String msg=d.switchTo().alert().getText();
                return !msg.isEmpty()?msg:null;
            }
            catch (Exception e)
            {
                return null;
            }

        });
    }

    //  type in  alert
    public   void sendTextToAlert(String  msg)
    {
        wait.fluentWait().until(d->{
            try {
                d.switchTo().alert().sendKeys(msg);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //  Alert  Authentication
    public  void AlertAuth(String userName,String password)
    {
        ((HasAuthentication)driver).register(
                url->true,
                UsernameAndPassword.of(userName,password)
        );
    }
}

