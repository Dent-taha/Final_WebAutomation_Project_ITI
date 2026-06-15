import com.project.drivers.GUI;
import com.project.drivers.WebDriverProvider;
import com.project.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {

    protected GUI driver;
    protected JsonReader testData;
    @Override
    public WebDriver getWebDriver() {
        return driver.getDriver();
    }

@BeforeClass
    public void beforeClass()
    {

    }




}
