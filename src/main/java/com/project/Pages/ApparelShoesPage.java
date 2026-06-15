package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import com.project.utils.logs.logsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ApparelShoesPage {

    private final  GUI  driver;
    public ApparelShoesPage(GUI driver) {
        this.driver=driver;
    }
    private final  String  endPoint="/apparel-shoes";


    //locators
    private final By addTOCartButton(String name)
    {
        return By.xpath("//h2[@class='product-title' and contains(.,'"+name+"')]/following::input");
    }
    private final  By productDetails(String productName)
    {
        return  By.xpath("//h2[@class='product-title' and contains(.,'"+productName+"')]//a");
    }

    // details page
    private final By productDetailsName=By.cssSelector("h1[itemprop=name]");
    private  final  By  inStock=By.cssSelector("div span[class='value']");


    private final By addToCartButton2(String id)
    {
        return By.id("add-to-cart-button-"+id);
    }


    private  final By addToCompareList=By.cssSelector(".compare-products input");
    private final  By comparePrice(String number)
    {
        return By.xpath("(//td[contains(.,'Price')]//following::td)["+number+"]");
    }
    private  final By compareName(String href)
    {
        return By.xpath("//a[@href='"+href+"']");
    }
    private  final  By clearListButton=By.cssSelector(".clear-list");
    private final By emptyListMessage=By.xpath("//div[@class='page-title']//following::div[@class='page-body']");




    private  final By addReviewButton=By.xpath("//div[@class='product-review-links']//span//following-sibling::a");

    private final By reviewErrMessage=By.cssSelector(".validation-summary-errors li");
    private final By reviewSuccessMessage=By.xpath("//div[@class='result']");
    private final By reviewTitle=By.id("AddProductReview_Title");
    private  final By reviewText=By.id("AddProductReview_ReviewText");
    private final  By addProductRating(String rate)
    {
        return By.id("addproductrating_"+rate);
    }
    private  final By submitReviewButton=By.cssSelector(".buttons input[type=submit]");





    private final  By addToCartMessage=By.xpath("//p[@class='content']");
            //The product has been added to your
    private final By viewCartButton=By.xpath("//p[@class='content']//a");

    private final  By productDetailsPrice=By.cssSelector(".product-price span");
    private  final  By ShortcutShoppingCart=By.cssSelector("a[href='/cart']");

    private final  By shortcutFirstProductName(String name)
    {
        return  By.xpath("//div[@class='item first']//div[@class='product']//a[.='"+name+"']");
    }
    private final  By shortcutAnyProductName(String name)
    {
        return  By.xpath("//div[@class='item']//div[@class='product']//a[.='"+name+"']");
    }
    private  final By shortcutFirstProductPrice (String name)
    {
        return By.xpath("//div[@class='item first']//div[@class='product']//a[.='"+name+"']//following::div//span");

    }
    private  final By shortcutAnyProductPrice (String name)
    {
        return By.xpath("//div[@class='item']//div[@class='product']//a[.='"+name+"']//following::div//span");

    }
    private final By getShortcutProductTotal=By.cssSelector(".totals strong");




    // actions
    @Step("navigate  to Apparel Shoes Page")
    public ApparelShoesPage  navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb")+endPoint);
        return this;
    }

    public ApparelShoesPage  addToCart(String name)
    {
        driver.elementsActions().click(addTOCartButton(name));
        return this;
    }
    public ApparelShoesPage  addToCart2(String id)
    {
        driver.elementsActions().click(addToCartButton2(id));
        return this;
    }
    public ApparelShoesPage clickOnProductDetails(String name)
    {
        driver.elementsActions().click(productDetails(name));
        return this;
    }

    public ApparelShoesPage hoverOnTopShortcutCart()
    {
        driver.elementsActions().hover2(ShortcutShoppingCart);
        return this;
    }
    @Step("click in  view cart  appeared  in success  message")
    public ApparelShoesPage clickOnViewCartFromSuccessMessage()
    {
        driver.elementsActions().click(viewCartButton);
        return this;
    }
    @Step("click  on  add review button")
    public ApparelShoesPage addReview()
    {
        driver.elementsActions().click(addReviewButton);
        return this;
    }
    @Step("adding review for specific product")
    public ApparelShoesPage sendReview(String text,String title,String rate)
    {
        driver.elementsActions().type(reviewText,text);
        driver.elementsActions().type(reviewTitle,title);
        driver.elementsActions().click(addProductRating(rate));
        return this;
    }
    @Step("click  in submit review button  to send review")
    public ApparelShoesPage submitReview()
    {
        driver.elementsActions().click(submitReviewButton);
        return this;
    }
    @Step("adding product ot compare list")
    public ApparelShoesPage addToCompareList()
    {
        driver.elementsActions().click(addToCompareList);
        return this;
    }

    @Step("clear th compare  list")
    public ApparelShoesPage  clearCompareList()
    {
        driver.elementsActions().click(clearListButton);
        return this;
    }
    //validation

    public ApparelShoesPage verifyMessageAddedToCart(String  msg)
    {
        String actualMsg=driver.elementsActions().getTxt(addToCartMessage);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
    public ApparelShoesPage verifyProductDetails(String  name,String inStock,String price)
    {
        String actualName=driver.elementsActions().getTxt(productDetailsName);
        String actualStatus=driver.elementsActions().getTxt(this.inStock);
        String actualPrice=driver.elementsActions().getTxt(productDetailsPrice);
        driver.validate().assertEquals(actualName,name);
        driver.validate().assertEquals(actualStatus,inStock);
        driver.validate().assertEquals(actualPrice,price);
        return this;
    }
    public ApparelShoesPage verifyFirstProductFromShortCutCart(String name,String price)
    {
        String actualName=driver.elementsActions().getTxt(shortcutFirstProductName(name));
        logsManager.info("actual name:",actualName);
        String actualPrice=driver.elementsActions().getTxt(shortcutFirstProductPrice(name));
        logsManager.info("actual  price :",actualPrice);
        driver.validate().assertEquals(actualName,name);
        driver.validate().assertEquals(actualPrice,price);
        return this;
    }
    public ApparelShoesPage verifyAnyProductFromShortCutCart(String name,String price)
    {
        String actualName=driver.elementsActions().getTxt(shortcutAnyProductName(name));
        String actualPrice=driver.elementsActions().getTxt(shortcutAnyProductPrice(name));
        driver.validate().assertEquals(actualName,name);
        driver.validate().assertEquals(actualPrice,price);
        return this;
    }
    @Step("verify  the total price of product added vto cart from Top cart icon")
    public ApparelShoesPage verifyTotalPriceFromShortcutIcon(String totalPrice)
    {

        String actualTotal=driver.elementsActions().getTxt(getShortcutProductTotal);
        logsManager.info("Total Price is  :",actualTotal);
        driver.verify().assertEquals(actualTotal,totalPrice);
        return this;
    }
    @Step("verify  view cart  button action")
    public ApparelShoesPage verifyViewCartButton(String  link)
    {
        String actualLink=driver.browserActions().getCurrentUrl();
        driver.verify().assertEquals(actualLink,link);
        return this;
    }
    @Step("verify error message  when review  without  login  ")
    public ApparelShoesPage verifyReviewErrorMessage(String msg)
    {
        String actualMessage=driver.elementsActions().getTxt(reviewErrMessage);
        logsManager.info("actual error  message: ",actualMessage);
        driver.verify().assertEquals(actualMessage,msg);
        return this;
    }
    @Step("verify successful  review message ")
    public ApparelShoesPage verifyReviewSuccessMessage(String msg)
    {
        String actualMessage=driver.elementsActions().getTxt(reviewSuccessMessage);
        logsManager.info("actual success message: ",actualMessage);
        driver.verify().assertEquals(actualMessage,msg);
        return this;
    }
    @Step("verify product details  in  compare  list  ")
    public ApparelShoesPage verifyCompareListDetails(String name,String  price,String  number,String  href)
    {
        String actualName=driver.elementsActions().getTxt(compareName(href));
        logsManager.info("actual name",actualName);
        String actualPrice=driver.elementsActions().getTxt(comparePrice(number));
        logsManager.info("actual price",actualPrice);
        driver.validate().assertEquals(actualName,name);
        driver.validate().assertEquals(actualPrice,price);

        return this;
    }
    @Step("verify action of empty list button")
    public ApparelShoesPage verifyEmptyList(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(emptyListMessage);
        logsManager.info("actual empty message:",actualMsg);
        driver.verify().assertEquals(actualMsg,msg);
        return this;

    }


}
