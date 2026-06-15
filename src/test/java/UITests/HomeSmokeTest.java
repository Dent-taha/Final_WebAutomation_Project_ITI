package UITests;

import Base.BaseTest;
import com.project.Pages.HomePage;
import com.project.drivers.GUI;
import com.project.drivers.UI;
import com.project.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@UI
public class HomeSmokeTest extends BaseTest {


@Test
public void     verifyHomeMessage()
{
    new HomePage(driver)
            .navigate()
            .verifyHomePage(testData.getJasonKey("messages.homePage"));
}
@Test(dependsOnMethods = "verifyHomeMessage")
public void verifyCartEmptyMessage()
{
    new HomePage(driver)
            .hoverOnCart()
            .verifyEmptyCart(testData.getJasonKey("messages.emptyCart"));
}




    //  config
    @BeforeClass
    public  void setup()
    {
        testData=new JsonReader("home_data");
        driver=new GUI();
    }
    @AfterClass
    public void tearDown()
    {
        driver.Quit();
    }
}
