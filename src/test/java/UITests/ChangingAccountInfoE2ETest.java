package UITests;

import Base.BaseTest;
import com.project.Pages.LoginPage;
import com.project.Pages.MyAccountPage;
import com.project.Pages.RegisterPage;
import com.project.drivers.GUI;
import com.project.drivers.UI;
import com.project.utils.TimeManager;
import com.project.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@UI
public class ChangingAccountInfoE2ETest extends BaseTest {
    String timeStamp= TimeManager.getTimeStamp();

@Test
public void registration()
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

    ;
}
    @Test(dependsOnMethods = "registration")
    public void login()
    {
        new LoginPage(driver)
                .navigate()
                .verifyLoginPage(testData.getJasonKey("message.welcome"))
                .enterLoginData( testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("valid.password"))
                .clickLogin()
                .verifySuccessfulLogin(testData.getJasonKey("valid.email")+timeStamp+"@gmail.com");
    }

    @Test(dependsOnMethods = {"registration","login"})
    public void verifyAccountInfo()
    {
        new MyAccountPage(driver)
                .navigate()
                .verifyDetails(
                        testData.getJasonKey("valid.first")
                        , testData.getJasonKey("valid.last"),
                        testData.getJasonKey("valid.email")+timeStamp+"@gmail.com"
                )
                .changeFirstname(testData.getJasonKey("newName"))
                .verifyDetails(
                        testData.getJasonKey("newName")
                        , testData.getJasonKey("valid.last"),
                        testData.getJasonKey("valid.email")+timeStamp+"@gmail.com"
                );
    }
    @Test(dependsOnMethods = {"registration","login","verifyAccountInfo"})
    public void addNewAddress()
    {
        new MyAccountPage(driver)
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
     @Test(dependsOnMethods = {"registration","login","verifyAccountInfo","addNewAddress"})

    public void changePassword()
    {
        new MyAccountPage(driver)
                .clickOnCustomerChangePassword()
                .changePassword(
                        testData.getJasonKey("valid.password"),
                        testData.getJasonKey("newPass")
                )
                .savePassword();
    }
    @Test(dependsOnMethods = {"registration","login","verifyAccountInfo","addNewAddress","changePassword"})
    public void verifyLoginWithNewPassword()
    {
        new LoginPage(driver)
                .navigate()
                .verifyLoginPage(testData.getJasonKey("message.welcome"))
                .enterLoginData( testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("newPass"))
                .clickLogin()
                .verifySuccessfulLogin(testData.getJasonKey("valid.email")+timeStamp+"@gmail.com");
    }

    // config

    @BeforeClass
    public void setup()
    {
        testData=new JsonReader("account");
        driver=new GUI();
    }
    @AfterClass
    public void tearDown()
    {
        driver.Quit();
    }
}
