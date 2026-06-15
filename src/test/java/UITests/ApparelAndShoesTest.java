package UITests;

import Base.BaseTest;
import com.project.Pages.ApparelShoesPage;
import com.project.Pages.HomePage;
import com.project.Pages.LoginPage;
import com.project.drivers.GUI;
import com.project.drivers.UI;
import com.project.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UI
public class ApparelAndShoesTest extends BaseTest {



@Test
public void verifyMessageAddedToCartProduct1()
{
    //verify add  product from  main  page
    new ApparelShoesPage(driver)
            .addToCart(testData.getJasonKey("product1.name"))
            .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))

            ;
}
@Test
    public void verifyProductDetailsAndToCart()
    {
        // verify  add  product  from details page
        new ApparelShoesPage(driver)
                .clickOnProductDetails(testData.getJasonKey("product2.name"))
                .verifyProductDetails(testData.getJasonKey("product2.name"),
                        testData.getJasonKey("product2.availability")
                , testData.getJasonKey("product2.price"))
                .addToCart2(testData.getJasonKey("product2.id"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"));
    }
    @Test
    public void addingTowProductsAndVerifyShortCutCartIcon()
    {
        new ApparelShoesPage(driver).
        clickOnProductDetails(testData.getJasonKey("product1.name"))
                . verifyProductDetails(testData.getJasonKey("product1.name"),
                testData.getJasonKey("product1.availability")
                , testData.getJasonKey("product1.price"))
                .addToCart2(testData.getJasonKey("product1.id"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .navigate()
                . clickOnProductDetails(testData.getJasonKey("product2.name"))
                . verifyProductDetails(testData.getJasonKey("product2.name"),
                        testData.getJasonKey("product2.availability")
                        , testData.getJasonKey("product2.price"))
                .addToCart2(testData.getJasonKey("product2.id"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .navigate()
                .hoverOnTopShortcutCart()
                .verifyFirstProductFromShortCutCart(testData.getJasonKey("product2.name"), testData.getJasonKey("product2.price"))
                .verifyAnyProductFromShortCutCart(testData.getJasonKey("product1.name"), testData.getJasonKey("product1.price"))
                .verifyTotalPriceFromShortcutIcon(testData.getJasonKey("total"));
    }

    @Test
    public void verifyReviewWithoutLogin()
    {
        new ApparelShoesPage(driver)
                .clickOnProductDetails(testData.getJasonKey("product1.name"))
                .addReview()
                .verifyReviewErrorMessage(testData.getJasonKey("message.errorReview"));
    }

    @Test
    public  void  verifyReviewWithLogin()
    {
        new LoginPage(driver)
                .navigate()
                .enterLoginData(testData.getJasonKey("login.email"), testData.getJasonKey("login.password"))
                .clickLogin()
                .verifySuccessfulLogin(testData.getJasonKey("login.email"));
        new ApparelShoesPage(driver)
                .navigate()
                .clickOnProductDetails(testData.getJasonKey("product1.name"))
                .addReview()
                .sendReview(
                        testData.getJasonKey("review.topic"),
                        testData.getJasonKey("review.title"),
                        testData.getJasonKey("review.veryGood")
                )
                .submitReview()
                .verifyReviewSuccessMessage(testData.getJasonKey("message.successReview"));
    }
    @Test
    public void verifyAddingTowProductsInCompareList()
    {
        new ApparelShoesPage(driver)
                .clickOnProductDetails(testData.getJasonKey("product1.name"))
                .addToCompareList()
                .navigate()
                .clickOnProductDetails(testData.getJasonKey("product2.name"))
                .addToCompareList()
                .verifyCompareListDetails(
                        testData.getJasonKey("product1.name"),
                        testData.getJasonKey("product1.price"),
                        "2",
                        testData.getJasonKey("product1.href")
                )
                .verifyCompareListDetails(
            testData.getJasonKey("product2.name"),
            testData.getJasonKey("product2.price"),
            "1",
            testData.getJasonKey("product2.href")
    );
    }

    @Test
    public void verifyEmptyList()
    {
      new ApparelShoesPage(driver)
            .clickOnProductDetails(testData.getJasonKey("product1.name"))
            .addToCompareList()
            .navigate()
            .clickOnProductDetails(testData.getJasonKey("product2.name"))
            .addToCompareList()
              .clearCompareList()
              .verifyEmptyList(testData.getJasonKey("message.emptyList"));

    }


//config

    @BeforeClass
    public void setData()
    {
        testData=new JsonReader("apparel");
    }
    @BeforeMethod
    public void setup()
    {
        driver=new GUI();
        new HomePage(driver)
                .navigate()
                .clickOnApparelShoes();
    }
    @AfterMethod
    public  void tearDown()
    {
        driver.Quit();
    }
}
