package com.project.Pages;

import com.project.drivers.GUI;
import com.project.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    private   final GUI driver  ;


    public HomePage(GUI driver)
    {
        this.driver=driver;
    }


    //vars
    //locators
    private final  By loginButton=By.xpath("//div[@class='header-links']//a[contains(.,'Log in')]");
    private final  By registerButton=By.xpath("//div[@class='header-links']//a[contains(.,'Register')]");
    private  final By shoppingCart=By.xpath("//div[@class='header-links']//a//span[.='Shopping cart']");
    private final By wishlist=By.xpath("//div[@class='header-links']//a//span[.='Wishlist']");
    private final  By logoutButton=By.cssSelector("a[href='/logout']");
    private final By searchArea=By.cssSelector(".search-box #small-searchterms");
    private final By submitSearch=By.cssSelector(".search-box  input[type='submit']");


    private final  By  topMenu(String  linkName)
    {
        return By.xpath("//ul[@class='top-menu']//a[contains(.,'"+linkName+"')]");
    }
    private  final   By cartMessage=By.cssSelector(".count");
    private final By subItems(String name)
    {
        return By.xpath("(//a[@href='/"+name+"'])[1]");
    }


    private  final  By listBox(String linkName)
    {
        return By.xpath("(//a[@href='/"+linkName+"'])[3]");
    }
    private final By homeMessage=By.cssSelector(".topic-html-content-title h2");

    //newsletter

    private final By newsLetterField=By.cssSelector(".newsletter-email #newsletter-email");
    private final By subscribe=By.cssSelector(".buttons #newsletter-subscribe-button");

    //  voting
    private final By voteOption(String vote)
    {
        return By.id("pollanswers-"+vote);
    }
    private final  By voteButton=By.cssSelector(".buttons #vote-poll-1");


    //actions
    @Step("navigate to home page ")
    public HomePage navigate()
    {
        driver.browserActions().navigate(PropertyReader.getProperty("baseURLWeb"));
        return  this;
    }
    @Step("click in register button ")
    public RegisterPage register()
    {
        driver.elementsActions().click(registerButton);
        return  new RegisterPage(driver);
    }
    @Step("click  on  login  button")
    public LoginPage login()
    {
        driver.elementsActions().click(loginButton);
        return new LoginPage(driver);
    }
    @Step("click  in  shoppingCart  Button")
    public ShoppingCart cart()
    {
        driver.elementsActions().click(shoppingCart);
        return new ShoppingCart(driver);
    }

    @Step("hover on shopping cart  button")
    public HomePage hoverOnCart()
    {
        driver.elementsActions().hover(shoppingCart);
        return this;
    }
    @Step("navigate to wishlist")
    public WishListPage wishlist()
    {
        driver.elementsActions().click(wishlist);
        return new WishListPage(driver);
    }


    // top  menu

    @Step("Navigate to Books page")
    public BooksPage clickOnBooks()
    {
        driver.elementsActions().click(topMenu("Books"));
        return new BooksPage(driver);
    }

    @Step("Navigate to Computers page")
    public ComputersPage clickOnComputers()
    {
        driver.elementsActions().click(topMenu("Computers"));
        return new ComputersPage(driver);
    }

    @Step("Navigate to Electronics page")
    public ElectronicsPage clickOnElectronics()
    {
        driver.elementsActions().click(topMenu("Electronics"));
        return new ElectronicsPage(driver);
    }

    @Step("Navigate to Apparel & Shoes page")
    public ApparelShoesPage clickOnApparelShoes()
    {
        driver.elementsActions().click(topMenu("Apparel & Shoes"));
        return new ApparelShoesPage(driver);
    }

    @Step("Navigate to Digital Downloads page")
    public DigitalDownloadsPage clickOnDigitalDownloads()
    {
        driver.elementsActions().click(topMenu("Digital downloads"));
        return new DigitalDownloadsPage(driver);
    }

    @Step("Navigate to Jewelry page")
    public JewelryPage clickOnJewelry()
    {
        driver.elementsActions().click(topMenu("Jewelry"));
        return new JewelryPage(driver);
    }

    @Step("Navigate to Gift Cards page")
    public GiftCardsPage clickOnGiftCards()
    {
        driver.elementsActions().click(topMenu("Gift Cards"));
        return new GiftCardsPage(driver);
    }



    //  list  box

    @Step("Navigate to Books page from list box")
    public BooksPage clickOnBooksFromList()
    {
        driver.elementsActions().click(listBox("books"));
        return new BooksPage(driver);
    }

    @Step("Navigate to Computers page from list box")
    public ComputersPage clickOnComputersFromList()
    {
        driver.elementsActions().click(listBox("computers"));
        return new ComputersPage(driver);
    }

    @Step("Navigate to Electronics page from list box")
    public ElectronicsPage clickOnElectronicsFromList()
    {
        driver.elementsActions().click(listBox("electronics"));
        return new ElectronicsPage(driver);
    }

    @Step("Navigate to Apparel & Shoes page from list box")
    public ApparelShoesPage clickOnApparelShoesFromList()
    {
        driver.elementsActions().click(listBox("apparel-shoes"));
        return new ApparelShoesPage(driver);
    }

    @Step("Navigate to Digital Downloads page from list box")
    public DigitalDownloadsPage clickOnDigitalDownloadsFromList()
    {
        driver.elementsActions().click(listBox("digital-downloads"));
        return new DigitalDownloadsPage(driver);
    }

    @Step("Navigate to Jewelry page from list box")
    public JewelryPage clickOnJewelryFromList()
    {
        driver.elementsActions().click(listBox("jewelry"));
        return new JewelryPage(driver);
    }

    @Step("Navigate to Gift Cards page from list box")
    public GiftCardsPage clickOnGiftCardsFromList()
    {
        driver.elementsActions().click(listBox("gift-cards"));
        return new GiftCardsPage(driver);
    }

    // serch
    public HomePage enterSearchItem(String  search)
    {
        driver.elementsActions().type(searchArea,search);
        driver.elementsActions().click(submitSearch);
        return this;
    }


    // vote
    public HomePage vote(String voteLevel)
    {
        driver.elementsActions().click(voteOption(voteLevel));
        driver.elementsActions().click(voteButton);
        return this;
    }

    public HomePage logout()
    {
        driver.elementsActions().click(logoutButton);
        return this;
    }


    //validations
    @Step("validate Home  Page")
    public HomePage verifyHomePage(String msg)
    {
        String actualMsg=driver.elementsActions().getTxt(homeMessage);
        driver.verify().assertEquals(actualMsg,msg);
        return this;
    }
@Step("verify empty  cart  just after navigating  to  home ")
public HomePage verifyEmptyCart(String msg)
{

    String actualMsg=driver.elementsActions().getTxt(cartMessage);
    driver.verify().assertEquals(actualMsg,msg);
    return this;

}

}
