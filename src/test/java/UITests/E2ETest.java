package UITests;

import Base.BaseTest;
import com.project.Pages.*;
import com.project.drivers.GUI;
import com.project.drivers.UI;
import com.project.utils.TimeManager;
import com.project.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@UI
public class E2ETest extends BaseTest {
    String timeStamp= TimeManager.getTimeStamp();

@Test
    public void register()
    {
        new RegisterPage(driver)
                .navigate()
                .verifyRegistrationPage()
                .enterRegistrationData(testData.getJasonKey("valid.gender"),
                        testData.getJasonKey("valid.first")
                        , testData.getJasonKey("valid.last"),
                        testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("valid.password"),
                        testData.getJasonKey("valid.confirm"))
                .clickRegisterButton()
                .verifySuccessfulRegistration(testData.getJasonKey("message.successfulRegistration"))
                .logout();
    }

    @Test(dependsOnMethods = "register")
    public void  login()
    {
        new LoginPage(driver)
                .navigate()
                .verifyLoginPage(testData.getJasonKey("message.welcome"))
                .enterLoginData( testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("valid.password"))
                .clickLogin()
                .verifySuccessfulLogin(testData.getJasonKey("valid.email")+timeStamp+"@gmail.com");

    }
@Test(dependsOnMethods = {"register","login"})
    public void addNewAddress()
    {
        new MyAccountPage(driver)
                .navigate()
                .clickOnCustomerAddress()
                .addNewAddress()
                .addAddressDetails(testData.getJasonKey("valid.first"),
                        testData.getJasonKey("valid.last"),
                        testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("address.company"),
                        testData.getJasonKey("address.city"),
                        testData.getJasonKey("address.address1"),
                        testData.getJasonKey("address.address2"),
                        testData.getJasonKey("address.zip"),
                        testData.getJasonKey("address.phone"),
                        testData.getJasonKey("address.fax"))
                .saveAddress()
                .verifyAddressDetails("Phone number: "+testData.getJasonKey("address.phone")
                        ,"Fax number: "+testData.getJasonKey("address.fax"),
                        testData.getJasonKey("address.address1"),
                        testData.getJasonKey("address.address2"),
                        testData.getJasonKey("address.country"),
                        testData.getJasonKey("address.city")+", "+testData.getJasonKey("address.zip"),
                        testData.getJasonKey("address.company"));
    }
    @Test(dependsOnMethods = {"register","login","addNewAddress"})
    public void addProduct()
    {
        //verify add  product from  main  page
        new ApparelShoesPage(driver)
                .navigate()
                .clickOnProductDetails(testData.getJasonKey("product1.name"))
                .addToCart2(testData.getJasonKey("product1.id"))
                .verifyMessageAddedToCart(testData.getJasonKey("message.addToCart"))
                .clickOnViewCartFromSuccessMessage()

        ;
    }
    @Test(dependsOnMethods = {"register","login","addNewAddress","addProduct"})
    public void checkOut()
    {
        new ShoppingCart(driver)
                .clickOnTermAndServicesCheckbox()
                .checkOut()
                .verifyCheckout();
    }

    @Test(dependsOnMethods = {"register","login","addNewAddress","addProduct","checkOut"})
    public void pay()
    {
        new CheckoutPage(driver)
                .billingContinue()
                .clickOnStorePick()
                .shippingContinue()
                .paymentMethodContinue()
                .paymentInfoContinue()
                .verifyProductDetails(
                        testData.getJasonKey("product1.name"),
                        testData.getJasonKey("product1.price")
                )
                .verifyBillingAddressDetails(
                        testData.getJasonKey("address.phone"),
                        testData.getJasonKey("address.address1"),
                        testData.getJasonKey("address.address2"),
                        testData.getJasonKey("address.country")
                )
                .verifyShippingMethod(testData.getJasonKey("method.shipping"))
                .verifyPaymentMethod(testData.getJasonKey("method.payment"))
                .verifyTotal(
                        testData.getJasonKey("payment.fee"),
                        testData.getJasonKey("payment.total")
                )
                .confirmOrder()
                .verifyConfirmationOrder(testData.getJasonKey("message.confirmOrder"));
    }







    // config

    @BeforeClass
    public void setup()
    {
        testData=new JsonReader("e2e");
        driver=new GUI();
    }
    @AfterClass
    public void tearDown()
    {
        driver.Quit();
    }}
