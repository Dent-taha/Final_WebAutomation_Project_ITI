package UITests;

import Base.BaseTest;
import com.project.Pages.*;
import com.project.drivers.GUI;
import com.project.drivers.UI;
import com.project.utils.TimeManager;
import com.project.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UI
public class CartFunctionalityTest extends BaseTest {



    String timeStamp= TimeManager.getTimeStamp();

    @Test
    public void verifyProductDetailsInCart()
    {
        new ApparelShoesPage(driver)
                .navigate()
                .addToCart(testData.getJasonKey("product1.name"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage();

        new ShoppingCart(driver)
                .verifyProductDetails(
                        testData.getJasonKey("product1.name"),
                        testData.getJasonKey("product1.price"),
                        testData.getJasonKey("product1.quantity"),
                        testData.getJasonKey("product1.total"),
                        testData.getJasonKey("product1.href")
                );
    }
    @Test
    public void verifyFunctionalityOfRemoveProductButton()
    {
        new ApparelShoesPage(driver)
                .navigate()
                .addToCart(testData.getJasonKey("product1.name"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage();

        new ShoppingCart(driver)
                .removeProduct("1")
                .updateCart()
                .verifyCartIsEmpty(testData.getJasonKey("message.emptyCart"));

    }
    @Test
    public void verifyNewTotalAfterIncreasingQuantity()
    {
        new ApparelShoesPage(driver)
                .navigate()
                .addToCart(testData.getJasonKey("product1.name"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage();

        new ShoppingCart(driver).
                verifyProductDetails(
            testData.getJasonKey("product1.name"),
            testData.getJasonKey("product1.price"),
            testData.getJasonKey("product1.quantity"),
            testData.getJasonKey("product1.total"),
            testData.getJasonKey("product1.href")
    )
        .increaseQuantity("3",testData.getJasonKey("product1.href"))
        .updateCart()
        .verifyProductDetails(
                testData.getJasonKey("product1.name"),
                testData.getJasonKey("product1.price"),
                testData.getJasonKey("product1.increaseQuantity"),
                testData.getJasonKey("product1.increasedTotal"),
                testData.getJasonKey("product1.href")
        );


    }
    @Test
    public void verifyFunctionalityOfContinueShoppingButton()
    {
        new ApparelShoesPage(driver)
                .navigate()
                .addToCart(testData.getJasonKey("product1.name"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage();

        new ShoppingCart(driver)
                .continueShopping();

        new ApparelShoesPage(driver)
                .navigate()
                .clickOnProductDetails(testData.getJasonKey("product2.name"))
                .addToCart2(testData.getJasonKey("product2.id"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage();
        new ShoppingCart(driver)
                .verifyProductDetails(
                        testData.getJasonKey("product1.name"),
                        testData.getJasonKey("product1.price"),
                        testData.getJasonKey("product1.quantity"),
                        testData.getJasonKey("product1.total"),
                        testData.getJasonKey("product1.href")
                )
                .verifyProductDetails(
                        testData.getJasonKey("product2.name"),
                        testData.getJasonKey("product2.price"),
                        testData.getJasonKey("product2.quantity"),
                        testData.getJasonKey("product2.total"),
                        testData.getJasonKey("product2.href")
                )
                .verifyTotalOrderPrice(testData.getJasonKey("totalOrder"));

    }


    @Test
    public void verifyTheProductISSavedAfterLogin()
    {
        new RegisterPage(driver)
                .navigate()
                .enterRegistrationData(testData.getJasonKey("valid.gender"),
                        testData.getJasonKey("valid.first")
                        , testData.getJasonKey("valid.last"),
                        testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("valid.password"),
                        testData.getJasonKey("valid.confirm"))
                .clickRegisterButton()
                .verifySuccessfulRegistration(testData.getJasonKey("message.successfulRegistration"))
                .logout()
                ;

        new ApparelShoesPage(driver)
                .navigate()
                .addToCart(testData.getJasonKey("product1.name"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage();

        new ShoppingCart(driver)
                .clickOnTermAndServicesCheckbox()
                .checkOut();

        new LoginPage(driver)
                .navigate()
                .enterLoginData( testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("valid.password"))
                .clickLogin()
                .verifySuccessfulLogin(testData.getJasonKey("valid.email")+timeStamp+"@gmail.com");

        new ShoppingCart(driver)
                .clickOnTermAndServicesCheckbox()
                .checkOut()
                .verifyCheckout();


    }
    @Test
    public void verifyServiceAndTermsButton()
    {
        new ApparelShoesPage(driver)
                .navigate()
                .addToCart(testData.getJasonKey("product1.name"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage();

        new ShoppingCart(driver)
                .checkOut()
                .verifyTermsAnsServiceMessage(testData.getJasonKey("message.terms_services"));


    }





    //config
    @BeforeClass
    public void beforeClass()
    {
        testData=new JsonReader("cart");
    }
    @BeforeMethod
    public void setup()
    {
        driver=new GUI();
        new HomePage(driver).navigate();
    }
    @AfterMethod
    public void tearDown()
    {
        driver.Quit();
    }



}
