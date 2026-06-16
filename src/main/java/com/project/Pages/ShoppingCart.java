package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import com.project.utils.logs.logsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ShoppingCart {
    private final GUI  driver;
    public ShoppingCart(GUI driver) {
        this.driver=driver;
    }

    //var
    private final String endPoint="/cart";
    //locators
    private final By updateCartBtn=By.cssSelector(".common-buttons input[name=updatecart]");

    private final By continueShoppingBtn=By.cssSelector(".common-buttons input[name=continueshopping]");


    private final By checkoutBtn=By.cssSelector("button[id=checkout]");


    private final By termAndServiceCheckBtn=By.cssSelector("input[id=termsofservice]");
        private  final By serviceAndTermsMessage=By.cssSelector("div[id=terms-of-service-warning-box] p");
        private final By removeMessageBtn=By.cssSelector("span[class='ui-button-icon-primary ui-icon ui-icon-closethick']");








    private final By productName(String href)
    {
        return By.xpath("//tr//a[@href='/"+href+"']");
    }
    private final By productPrice(String href)
    {
        return By.xpath("//tr//a[@href='/"+href+"']//following::td//span[@class='product-unit-price']");

    }
    private final By productQuantity(String href)
    {
        return By.xpath("//tr//a[@href='/"+href+"']//following::td//input");

    }

    private final By productSubTotal(String href)
    {
        return By.xpath("(//tr//a[@href='/"+href+"']//following::td//span[@class='product-subtotal'])[1]");

    }
    private final By removeProductButton(String productNumber)
    {
        return By.xpath("(//tr//a//preceding::input[@type='checkbox'])["+productNumber+"]");
    }

    private final  By emptyCartMessage=By.cssSelector(".order-summary-content");
    private final By totalOrder=By.xpath("//span[@class='product-price']");





    //actions
    @Step ("navigation to shopping cart")
    public ShoppingCart navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb")+endPoint);
        return this;
    }
    @Step("click  on remove  product  from cart")
    public ShoppingCart removeProduct(String number)
    {
        driver.elementsActions().click(removeProductButton(number));
        return this;

    }
    @Step("update cart ")
    public ShoppingCart updateCart()
    {
        driver.elementsActions().click(updateCartBtn);
        return this;
    }
    @Step ("increasing quantity")
    public ShoppingCart increaseQuantity(String quantity,String href)
    {
        driver.elementsActions().type(productQuantity(href),quantity);
        return this;
    }
    @Step("continue shopping ")
    public ShoppingCart continueShopping()
    {
        driver.elementsActions().click(continueShoppingBtn);
        return this;
    }

    @Step("check out")
    public ShoppingCart checkOut()
    {
        driver.elementsActions().click(checkoutBtn);
        return this;
    }

    @Step("remove message  of service and terms")
    public ShoppingCart removeServiceMessage()
    {
        driver.elementsActions().click(removeMessageBtn);
        return this;
    }

    @Step("click on check box of service and term")
    public ShoppingCart clickOnTermAndServicesCheckbox()
    {
        driver.elementsActions().click(termAndServiceCheckBtn);
        return this;
    }

    //validation
    @Step("verify product details in cart  ")
    public ShoppingCart verifyProductDetails(String name,String price,String quantity,String total,String href)
    {
        String actualProductName=driver.elementsActions().getTxt(productName(href));
        String actualProductPrice=driver.elementsActions().getTxt(productPrice(href));
        String actualQuantity=driver.elementsActions().getValue(productQuantity(href));
        String actualSubtotal=driver.elementsActions().getTxt(productSubTotal(href));
        driver.validate().assertEquals(actualProductName,name);
        driver.verify().assertEquals(actualProductPrice,price);
        driver.verify().assertEquals(actualQuantity,quantity);
        driver.verify().assertEquals(actualSubtotal,total);
        return this;

    }
    @Step("verify functionality  of remove  product from cart")
    public ShoppingCart verifyCartIsEmpty(String message)
    {
        String actualMessage=driver.elementsActions().getTxt(emptyCartMessage);
        logsManager.info("empty cart message: ",actualMessage);
        driver.verify().assertEquals(actualMessage,message);
        return this;
    }
    @Step("verify total  price Of order")
    public ShoppingCart verifyTotalOrderPrice(String price)
    {
        String actualPrice=driver.elementsActions().getTxt(totalOrder);
        driver.verify().assertEquals(actualPrice,price);
        return this;
    }
    public ShoppingCart verifyTermsAnsServiceMessage(String msg)
    {
        String actualMessage=driver.elementsActions().getTxt(serviceAndTermsMessage);
        driver.verify().assertEquals(actualMessage,msg);
        return this;
    }


    public ShoppingCart verifyCheckout()
    {
        driver.verify()
                .assertEquals(driver.browserActions().getCurrentUrl()
                ,"https://demowebshop.tricentis.com/onepagecheckout");
        return this;
    }
}
