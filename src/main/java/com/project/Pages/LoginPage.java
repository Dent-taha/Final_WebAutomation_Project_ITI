package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import groovy.util.logging.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage


{
    private final GUI driver;
    public LoginPage(GUI driver) {
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


    private final By recoveryButton=By.cssSelector("div[class='buttons'] input[type='submit']");
    private final  By emailRecoveryField=By.id("Email");
    private final By recoveryErrMsg=By.cssSelector("span span[for='Email']");
    private final By recoverSuccessfulMsg=By.cssSelector("div div[class=result]");
    private final By recoverFailedMsg=By.cssSelector("div div[class=result]");




    // actions

    @Step("navigate to  login  page  ")
    public LoginPage navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb")+endPoint);
        return this;
    }
    @Step("enter  credentials")
    public LoginPage enterLoginData(String email, String password)
    {
        driver.elementsActions().type(emailField,email);
        driver.elementsActions().type(passwordField,password);
        return this;
    }
    @Step("click  login ")
    public LoginPage clickLogin()
    {
     driver.elementsActions().click(loginButton);
     return this;
    }
    @Step("click  register ")
    public LoginPage clickRegister()
    {
        driver.elementsActions().click(registerButton);
        return this;
    }

    public LoginPage forgetPass()
    {
        driver.elementsActions().click(passwordRecovery);
        return this;
    }
    public  LoginPage enterRecoverAccount(String account)
    {
        driver.elementsActions().type(emailRecoveryField,account);
        return this;
    }
    public LoginPage clickRecover()
    {
        driver.elementsActions().click(recoveryButton);
        return this;
    }

    //  validations

    public LoginPage verifyLoginPage(String  msg)
    {
        String loginMessage=driver.elementsActions().getTxt(welcomeMsg);
        driver.verify().assertEquals(loginMessage,msg);
        return this;
    }

    public LoginPage verifyEmptyErrMsg(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(emptyMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public LoginPage verifyWrongFormMessage(String msg)
    {
        String  actualMsg=driver.elementsActions().getTxt(invalidFormAccount);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public LoginPage verifyWrongAccountErrMsg(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(wrongAccount);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public LoginPage verifyWrongPasswordMsg(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(wrongPassMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public LoginPage verifySuccessfulLogin(String msg)
    {
        String  actualMsg=driver.elementsActions().getTxt(headerAccountName);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }

    public LoginPage verifyRegisterButton(String expectedLink)
    {
        String actualLink=driver.browserActions().getCurrentUrl();
        driver.verify().assertEquals(actualLink,expectedLink);
        return this;
    }

    //
    public LoginPage verifySuccessfulRecovery(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(recoverSuccessfulMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public LoginPage verifyFailedRecovery(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(recoverSuccessfulMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }

    public LoginPage verifyWrongAccountFormRecovery(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(recoveryErrMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }

    public LoginPage verifyEmptyAccountRecovery(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(recoveryErrMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }



}
