package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Login


{
    private final GUI driver;
    public Login(GUI driver) {
        this.driver=driver;
    }
    //var
    String endPoint="/login";

    //locators
    private final By welcomeMsg=By.cssSelector(".page-title h1");
    private final  By emailField=By.id("Email");
    private  final  By passwordField=By.id("Password");
    private final By loginButton=By.cssSelector("div[class='buttons'] input[type='submit']");
    private  final  By  registerButton=By.cssSelector("div[class='buttons'] input[value='Register']");


    private final By emptyMsg=By.cssSelector("div[class=\"validation-summary-errors\"] span");
    private final By wrongPassMsg=By.cssSelector("div[class=\"validation-summary-errors\"]  li");
    private final By wrongAccount=By.cssSelector("div[class=\"validation-summary-errors\"]  li");
    private final By  invalidFormAccount=By.cssSelector("span span[for=Email]");

    private  final By passwordRecovery=By.cssSelector("a[href='/passwordrecovery']");

    private final By headerAccountName=By.cssSelector("div[class='header-links'] a[href='/customer/info']");


    // actions
    @Step("navigate to  login  page  ")
    public Login navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb")+endPoint);
        return this;
    }
    @Step("enter  credentials")
    public Login enterLoginData(String email,String password)
    {
        driver.elementsActions().type(emailField,email);
        driver.elementsActions().type(passwordField,password);
        return this;
    }
    @Step("click  login ")
    public Login clickLogin()
    {
     driver.elementsActions().click(loginButton);
     return this;
    }
    @Step("click  register ")
    public Login clickRegister()
    {
        driver.elementsActions().click(registerButton);
        return this;
    }

    public  Login forgetPass()
    {
        driver.elementsActions().click(passwordRecovery);
        return this;
    }

    //  validations

    public  Login verifyLoginPage(String  msg)
    {
        String loginMessage=driver.elementsActions().getTxt(welcomeMsg);
        driver.verify().assertEquals(loginMessage,msg);
        return this;
    }

    public Login verifyEmptyErrMsg(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(emptyMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public Login verifyWrongFormMessage(String msg)
    {
        String  actualMsg=driver.elementsActions().getTxt(invalidFormAccount);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }


}
