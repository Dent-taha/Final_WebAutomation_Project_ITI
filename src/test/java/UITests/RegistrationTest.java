package UITests;

import Base.BaseTest;
import com.project.Pages.HomePage;
import com.project.Pages.RegisterPage;
import com.project.drivers.GUI;
import com.project.drivers.UI;
import com.project.utils.TimeManager;
import com.project.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UI
public class RegistrationTest extends BaseTest {
    String timeStamp= TimeManager.getTimeStamp();

    @Test
    public void validRegistration()
    {
        new RegisterPage(driver)
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
    @Test(dependsOnMethods = "validRegistration")
    public void invalidRegistrationUsingTheSameAccount()
    {
        new HomePage(driver)
                .register()
                .enterRegistrationData(
                        testData.getJasonKey("valid.gender"),
                        testData.getJasonKey("valid.first")
                        , testData.getJasonKey("valid.last"),
                        testData.getJasonKey("valid.email")+timeStamp+"@gmail.com",
                        testData.getJasonKey("valid.password"),
                        testData.getJasonKey("valid.confirm")
                )
                .clickRegisterButton()
                .verifyAccountExistMsg(testData.getJasonKey("message.accountExisted"));


    }
    @Test
    public void verifyEmptyRegistrationData()
    {
        new RegisterPage(driver)
                .clickRegisterButton()
                .verifyEmptyDataErrorMessages(testData.getJasonKey("errorMessage.firstName"),
                        testData.getJasonKey("errorMessage.lastName"),
                        testData.getJasonKey("errorMessage.email"),
                        testData.getJasonKey("errorMessage.password"),
                        testData.getJasonKey("errorMessage.confirm"));
    }







//  config

    @BeforeClass
    public void setData()
    {
        testData=new JsonReader("register");
    }
    @BeforeMethod
    public void setup()
    {
        driver=new GUI();
        new HomePage(driver)
                .navigate()
                .register();
    }
    @AfterMethod
    public  void tearDown()
    {
        driver.Quit();
    }
}
