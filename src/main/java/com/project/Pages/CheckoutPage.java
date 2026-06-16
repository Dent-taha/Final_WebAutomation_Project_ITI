package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {


    private final GUI driver;
    public CheckoutPage(GUI _driver)
    {
        driver=_driver;
    }

    //vars
    private final String endPoint="/onepagecheckout";

    //locator
    private final By addressDetails=By.xpath("(//select//option)[1]");
    private final By billingAddressButton=By.xpath("//div[@id='billing-buttons-container']//input");




    private  final By inStorePickUp=By.id("PickUpInStore");
    private final By shippingAddress=By.xpath("//div[@id='shipping-buttons-container']//input");




    private final By paymentMethod=By.id("paymentmethod_0");
    private final By paymentContinue=By.xpath("//div[@id='payment-method-buttons-container']//input");


    private final By paymentInfoContinue=By.xpath("//div[@id='payment-info-buttons-container']//input");




    private final  By confirmOrderBtn=By.xpath("//div[@id='confirm-order-buttons-container']//input");


        private  final By productName=By.xpath("//td//a[@href='/blue-jeans']");
        private  final By productPrice=By.cssSelector(".product-unit-price");
        private final By  additionalFee=By.xpath("(//span[@class='product-price'])[3]");

        private final By totalOrder=By.xpath("//span[@class='product-price order-total']//strong");


        //Billing Address
    private final By phone=By.cssSelector(".billing-info .phone");
    private final By address1=By.cssSelector(".billing-info .address1");
    private final By address2=By.cssSelector(".billing-info .address2");
    private final By country=By.cssSelector(".billing-info .country");



        //Payment Method
    private final  By paymentMethod_v=By.xpath("//li[@class='payment-method']");
        //Cash On Delivery (COD)

    //Shipping Method
    private final By shippingMethod=By.xpath("//li[@class='shipping-method']");


    private final By confirmationMessage=By.xpath("//div/strong");
            //Your order has been successfully processed!


    //actions
    public CheckoutPage navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb")+endPoint);
        return this;
    }
    public CheckoutPage billingContinue()
    {
        driver.elementsActions().click(billingAddressButton);
        return this;
    }
    public CheckoutPage clickOnStorePick()
    {
        driver.elementsActions().click(inStorePickUp);
        return this;
    }

    public CheckoutPage shippingContinue()
    {
        driver.elementsActions().click(shippingAddress);
        return this;
    }
    public CheckoutPage paymentInfoContinue()
    {
        driver.elementsActions().click(paymentInfoContinue);
        return this;
    }

    public CheckoutPage paymentMethodContinue()
    {
        driver.elementsActions().click(paymentMethod);
        driver.elementsActions().click(paymentContinue);
        return this;

    }
    public CheckoutPage confirmOrder()
    {
        driver.elementsActions().click(confirmOrderBtn);
        return this;
    }
    //validation
    public CheckoutPage verifyProductDetails(String name,String price)
    {
        String actualName=driver.elementsActions().getTxt(this.productName);
        String ActualPrice=driver.elementsActions().getTxt(this.productPrice);
        driver.validate().assertEquals(actualName,name);
        driver.validate().assertEquals(ActualPrice,price);
        return this;

    }
    public  CheckoutPage verifyTotal(String fee,String total)
    {
        String actualFee=driver.elementsActions().getTxt(additionalFee);
        String actualTotal=driver.elementsActions().getTxt(totalOrder);
        driver.validate().assertEquals(actualTotal,total);
        driver.validate().assertEquals(actualFee,fee);
        return this;
    }
    public CheckoutPage verifyBillingAddressDetails(String phone,String address1,String address2,String country)
    {
        String actualPhone=driver.elementsActions().getTxt(this.phone);
        String actualAddress1=driver.elementsActions().getTxt(this.address1);
        String actualAddress2=driver.elementsActions().getTxt(this.address2);
        String actualCountry=driver.elementsActions().getTxt(this.country);
        driver.validate().assertEquals(actualPhone,"Phone: "+phone);
        driver.validate().assertEquals(actualAddress1,address1);
        driver.validate().assertEquals(actualAddress2,address2);
        driver.validate().assertEquals(actualCountry,country);
        return this;
    }
    public CheckoutPage verifyShippingMethod(String method)
    {
        String actualMethod=driver.elementsActions().getTxt(shippingMethod);
        driver.verify().assertEquals(actualMethod,method);
        return this;
    }

    public CheckoutPage verifyPaymentMethod(String method)
    {
        String actualMethod=driver.elementsActions().getTxt(paymentMethod_v);
        driver.verify().assertEquals(actualMethod,method);
        return this;
    }
    public CheckoutPage verifyConfirmationOrder(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(confirmationMessage);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }

}
