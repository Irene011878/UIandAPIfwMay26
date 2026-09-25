package setUp;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.configReader.ConfigReader;

public class BaseApiTest {

    @BeforeClass(alwaysRun = true)
    public void setUpApi() {

        RestAssured.baseURI =
                ConfigReader.getProperty("base.url");
    }
}
