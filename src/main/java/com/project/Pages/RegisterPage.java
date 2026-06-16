package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import com.project.utils.logs.logsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage {
    private final GUI driver;
    public RegisterPage(GUI driver) {
        this.driver=driver;
    }

    // vars
    String endPoint="/register";

    //  locators
    private final By logout=By.xpath("//a[@href='/logout']");

    public RegisterPage logout()
    {
        driver.elementsActions().click(logout);
        return this;
    }
    private final By registrationMsg= By.cssSelector(".page-title h1");
    private final  By selectGender(String gender)
    {
        return By.id("gender-"+gender);
    }
    private final By firstName=By.id("FirstName");
    private final By lastName=By.id("LastName");
    private final  By email=By.id("Email");
    private final  By password=By.id("Password");
    private  final By confirmPassword=By.id("ConfirmPassword");
    private final By registerButton=By.id("register-button");
    private  final  By errMessage(String name)
    {
        return By.cssSelector("span[for='"+name+"']");
    }
    private final By accountExistMsg=By.cssSelector("div[class='validation-summary-errors'] li");




private final By confirmRegistration=By.cssSelector(".page-body .result");
    private  final  By continueButton=By.cssSelector("input[value='Continue']");
    //actions
    @Step("navigate  to Registration Page")
    public RegisterPage navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb")+endPoint);
        return this;
    }
    @Step("enter valid registration data")
    public RegisterPage enterRegistrationData(String gender,
                                              String firstname,
                                              String lastName,
                                              String email,
                                              String password,
                                              String confirmPassword)
    {
        driver.elementsActions().click(selectGender(gender));
        driver.elementsActions().type(this.firstName,firstname);
        driver.elementsActions().type(this.lastName,lastName);
        driver.elementsActions().type(this.email,email);
        driver.elementsActions().type(this.password,password);
        driver.elementsActions().type(this.confirmPassword,confirmPassword);
        return this;
    }
    @Step("enter register  button")
    public RegisterPage  clickRegisterButton()
    {
        driver.elementsActions().click(registerButton);
        return  this;
    }
    public HomePage continueToHome()
    {
        driver.elementsActions().click(continueButton);
        return new HomePage(driver);
    }

    //validation
    public RegisterPage verifyRegistrationPage()
    {
      String msg=  driver.elementsActions().getTxt(registrationMsg);
        logsManager.info("actual  label:",msg);
        driver.verify().assertEquals(msg,"Register");
        return this;
    }
    public RegisterPage verifySuccessfulRegistration(String msg)
    {
        String actualMNsg=driver.elementsActions().getTxt(confirmRegistration);
        logsManager.info("actual message ", actualMNsg);
        driver.verify().assertEquals(actualMNsg,msg);
        return this;
    }
    public RegisterPage verifyAccountExistMsg(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(accountExistMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public RegisterPage verifyEmptyDataErrorMessages(
            String firstNAme,String lastName,String email,String password,String confirm
    )
    {
        String actualFirstnameMsg=driver.elementsActions().getTxt(errMessage("FirstName"));
        String actualLastNameMsg=driver.elementsActions().getTxt(errMessage("LastName"));
        String actualEmailMsg=driver.elementsActions().getTxt(errMessage("Email"));
        String actualPasswordMsg=driver.elementsActions().getTxt(errMessage("Password"));
        String actualConfirmedPasswordMsg=driver.elementsActions().getTxt(errMessage("ConfirmPassword"));

        driver.validate().assertEquals(actualFirstnameMsg,firstNAme);
        driver.validate().assertEquals(actualLastNameMsg,lastName);
        driver.validate().assertEquals(actualEmailMsg,email);
        driver.validate().assertEquals(actualPasswordMsg,password);
        driver.validate().assertEquals(actualConfirmedPasswordMsg,confirm);
        return this;
    }
}
