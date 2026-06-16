package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import com.project.utils.logs.logsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MyAccountPage {
    private  final GUI driver;
    public MyAccountPage(GUI driver)
    {
     this.driver=driver;
    }
    //var
    private final  String endPoint="/customer/info";
    //locators

    //customer Info
    private final By customerInfo=By.xpath("//ul[@class='list']//a[@href='/customer/info']");
        private  final By firstName=By.id("FirstName");
        private final  By lastName=By.id("LastName");
        private final  By email=By.id("Email");
        private final By submitBtn=By.cssSelector(".buttons input");




    private final  By customerAddresses=By.xpath("//ul[@class='list']//a[@href='/customer/addresses']");
        private final By addNewAddressBtn=By.cssSelector("input[class='button-1 add-address-button']");//click
        private final By addressFirstName=By.id("Address_FirstName");
        private final By addressLastName=By.id("Address_LastName");
        private final By addressEmail=By.id("Address_Email");
        private final By addressCompany=By.id("Address_Company");
        private  final By addressCountry=By.id("Address_CountryId");
        private final By addressCity=By.id("Address_City");
        private  final By addressAddress1=By.id("Address_Address1");
        private final By addressAddress2=By.id("Address_Address2");
        private final By addressZip=By.id("Address_ZipPostalCode");
        private final By phone=By.id("Address_PhoneNumber");
        private  final By faxNumber=By.id("Address_FaxNumber");
        private final By saveAddressBtn=By.cssSelector("input[class='button-1 save-address-button']");
        private final By verifyPhone=By.cssSelector(".phone");
        private final By verifyFax=By.cssSelector(".fax");
        private final By verifyAddress1=By.cssSelector(".address1");
        private final By verifyAddress2=By.cssSelector(".address2");
        private  final By verifyCountry=By.cssSelector(".country");
        private final By verifyCityStateZip=By.cssSelector(".city-state-zip");
        private final By verifyCompany=By.cssSelector(".company");



    private final  By getCustomerChangePassword=By.xpath("//ul[@class='list']//a[@href='/customer/changepassword']");
        private final By oldPassword=By.id("OldPassword");
        private final By newPassword=By.id("NewPassword");
        private final By confirmPassword=By.id("ConfirmNewPassword");
        private final By savePasswordButton=By.cssSelector("input[class='button-1 change-password-button']");

    //actions
    @Step("navigate to Account Information")
    public  MyAccountPage navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb")+endPoint);
        return this;
    }
    @Step("changing first name")
    public MyAccountPage changeFirstname(String newName)
    {
        driver.elementsActions().type(firstName,newName);
        return this;
    }
    @Step("click  on  customer address  hyperlink")
    public MyAccountPage clickOnCustomerAddress()
    {
        driver.elementsActions().click(customerAddresses);
        return this;
    }
    @Step ("click  on  add  new address")
    public MyAccountPage addNewAddress()
    {
        driver.elementsActions().click(addNewAddressBtn);
        return this;
    }
    @Step("enter addresses details")
    public MyAccountPage addAddressDetails(
            String fName,String lName,String email,String company,String city,String address1,
            String address2,String zipCod,String phone,String fax
    )
    {
        driver.elementsActions().type(addressFirstName,fName);
        driver.elementsActions().type(addressLastName,lName);
        driver.elementsActions().type(addressEmail,email);
        driver.elementsActions().type(addressCompany,company);
        driver.dropDownActions().selectBox(addressCountry,"31");
        driver.elementsActions().type(addressCity,city);
        driver.elementsActions().type(addressAddress1,address1);
        driver.elementsActions().type(addressAddress2,address2);
        driver.elementsActions().type(addressZip,zipCod);
        driver.elementsActions().type(this.phone,phone);
        driver.elementsActions().type(this.faxNumber,fax);
        return this;
    }
    @Step("save  new  addresses")
    public MyAccountPage saveAddress()
    {
        driver.elementsActions().click(saveAddressBtn);
        return this;
    }
    @Step("navigate  to change  password  hyperlink ")
    public MyAccountPage clickOnCustomerChangePassword()
    {
        driver.elementsActions().click(getCustomerChangePassword);
        return this;
    }
    @Step("enter new  password")
    public MyAccountPage changePassword(String  oldPassword,String newPassword)
    {
        driver.elementsActions().type(this.oldPassword,oldPassword);
        driver.elementsActions().type(this.newPassword,newPassword);
        driver.elementsActions().type(confirmPassword,newPassword);
        return this;
    }
    @Step("save  new  password")
    public MyAccountPage savePassword()
    {
        driver.elementsActions().click(savePasswordButton);
        return this;
    }

    //validations
    @Step("verify account information details")
    public MyAccountPage verifyDetails(
            String fName,
            String lName,
            String email
    )
    {
        String actualName=driver.elementsActions().getValue(this.firstName);
        logsManager.info("actual first name :",actualName);
        String actualLastName=driver.elementsActions().getValue(lastName);
        logsManager.info("actual last name: ",actualLastName);
        String ActualEmail=driver.elementsActions().getValue(this.email);
        logsManager.info("actual email: ",ActualEmail);
        driver.validate().assertEquals(actualName,fName);
        driver.validate().assertEquals(actualLastName,lName);
        driver.validate().assertEquals(ActualEmail,email);
        return this;
    }
    @Step("verify te  correctness added address  details")
    public MyAccountPage verifyAddressDetails(
            String phone,String fax,String address1,String address2,String country,String cityZip,String company
    )
    {
        String actualPhone = driver.elementsActions().getTxt(verifyPhone);
        logsManager.info("Actual phone number: {}", actualPhone);

        String actualFax = driver.elementsActions().getTxt(verifyFax);
        logsManager.info("Actual fax number: {}", actualFax);

        String actualAddress1 = driver.elementsActions().getTxt(verifyAddress1);
        logsManager.info("Actual address 1: {}", actualAddress1);

        String actualAddress2 = driver.elementsActions().getTxt(verifyAddress2);
        logsManager.info("Actual address 2: {}", actualAddress2);

        String actualCountry = driver.elementsActions().getTxt(verifyCountry);
        logsManager.info("Actual country: {}", actualCountry);

        String actualCityZip = driver.elementsActions().getTxt(verifyCityStateZip);
        logsManager.info("Actual city/state/zip: {}", actualCityZip);

        String actualCompany = driver.elementsActions().getTxt(verifyCompany);
        logsManager.info("Actual company: {}", actualCompany);

        driver.verify().assertEquals(actualPhone, phone);
        driver.verify().assertEquals(actualFax, fax);
        driver.verify().assertEquals(actualAddress1, address1);
        driver.verify().assertEquals(actualAddress2, address2);
        driver.verify().assertEquals(actualCountry, country);
        driver.verify().assertEquals(actualCityZip, cityZip);
        driver.verify().assertEquals(actualCompany, company);

        return this;
    }
}
