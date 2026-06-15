package UITests;

import Base.BaseTest;
import com.project.Pages.HomePage;
import com.project.Pages.LoginPage;
import com.project.drivers.GUI;
import com.project.drivers.UI;
import com.project.utils.dataReader.JsonReader;
import com.project.utils.dataReader.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@UI
public class LoginTest extends BaseTest {
    @Test
    public void verifyValidLogin()
    {
        new LoginPage(driver)
                .verifyLoginPage(testData.getJasonKey("message.welcome"))
                .enterLoginData(testData.getJasonKey("valid.email"),
                        testData.getJasonKey("valid.password"))
                .clickLogin()
                .verifySuccessfulLogin(testData.getJasonKey("valid.email"));
    }

    @Test
    public void  invalidLoginEmptyData()
    {
        new  LoginPage(driver)
                .clickLogin()
                .verifyEmptyErrMsg(testData.getJasonKey("message.emptyData"));
    }

    @Test
    public  void invalidLoginInvalidAccountForm()
    {
        new LoginPage(driver)
                .enterLoginData(",sjdhsdjkhfjksdhfksdhfksdjhfkdsjh","")
                .verifyWrongFormMessage(testData.getJasonKey("message.wrongForm"));
    }

    @Test
    public  void invalidLoginInvalidAccount()
    {
        new LoginPage(driver)
                .enterLoginData("11"+testData.getJasonKey("valid.email"), testData.getJasonKey("valid.password"))
                .clickLogin()
                .verifyWrongPasswordMsg(testData.getJasonKey("message.wrongAccount"));
    }

    @Test
    public  void invalidLoginInvalidPassword()
    {
        new LoginPage(driver)
                .enterLoginData(testData.getJasonKey("valid.email"), testData.getJasonKey("valid.password")+"11")
                .clickLogin()
                .verifyWrongAccountErrMsg(testData.getJasonKey("message.wrongPass"));
    }

    @Test
    public void verifyActionOfRegisterButton()
    {
        new  LoginPage(driver)
                .clickRegister()
                .verifyRegisterButton(PropertyReader.getProperty("baseURLWeb")+"/register");
    }

    @Test
    public void verifyPasswordRecovery()
    {
        new LoginPage(driver)
                .forgetPass()
                .enterRecoverAccount(testData.getJasonKey("valid.email"))
                .clickRecover()
                .verifySuccessfulRecovery(testData.getJasonKey("messageRecovery.success"));
    }
    @Test
    public void verifyWrongEmailRecovery()
    {
        new LoginPage(driver)
                .forgetPass()
                .enterRecoverAccount("lkasjkl"+testData.getJasonKey("valid.email"))
                .clickRecover()
                .verifyFailedRecovery(testData.getJasonKey("messageRecovery.wrongAccount"));
    }

    @Test
    public void verifyWrongAccountFormRecovery()
    {
        new LoginPage(driver)
                .forgetPass()
                .enterRecoverAccount("w,ejhfjekjrhgkjehgkjehkldhjksjewhfj")
                .clickRecover()
                .verifyWrongAccountFormRecovery(testData.getJasonKey("messageRecovery.wrongForm"));
    }

    @Test
    public void verifyEmptyAccountFormRecovery()
    {
        new LoginPage(driver)
                .forgetPass()
                .enterRecoverAccount("")
                .clickRecover()
                .verifyEmptyAccountRecovery(testData.getJasonKey("messageRecovery.empty"));
    }






    @BeforeClass
    public void setData()
    {
        testData=new JsonReader("login");
    }
    @BeforeMethod
    public void setup()
    {
        driver=new GUI();
        new HomePage(driver)
                .navigate()
                .login();
    }
    @AfterMethod
    public  void tearDown()
    {
        driver.Quit();
    }
}
