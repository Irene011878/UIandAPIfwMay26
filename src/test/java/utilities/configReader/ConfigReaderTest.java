/*package utilities.configReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigReaderTest {

    @Test
    public void shouldFallBackToConfigPropertiesWhenTestEnvIsNotSet() {

        Assert.assertEquals(
                ConfigReader.getProperty("environment"),
                "QA",
                "The environment should be read from the TEST_ENV system property."
        );

    }
}*/

/*package utilities.configReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigReaderTest {

    @Test
    public void shouldReadEnvironmentFromTestEnvSystemProperty() {

        String expectedEnvironment = System.getProperty("TEST_ENV");

        if (expectedEnvironment == null || expectedEnvironment.trim().isEmpty()) {
            return;
        }

        Assert.assertEquals(
                ConfigReader.getProperty("environment"),
                expectedEnvironment,
                "ConfigReader should return the TEST_ENV system property."
        );
    }

    @Test
    public void shouldFallBackToConfigPropertiesWhenTestEnvIsNotSet() {

        if (System.getenv("TEST_ENV") != null
                || System.getProperty("TEST_ENV") != null) {
            return;
        }

        Assert.assertEquals(
                ConfigReader.getProperty("environment"),
                "QA",
                "The environment should fall back to config.properties."
        );
    }
}*/

package utilities.configReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigReaderTest {

    @Test
    public void shouldReadEnvironmentFromTestEnvVariableOrSystemProperty() {

        String expectedEnvironment = System.getenv("TEST_ENV");

        if (expectedEnvironment == null || expectedEnvironment.isBlank()) {
            expectedEnvironment = System.getProperty("TEST_ENV");
        }

        // Permite ejecutar localmente si no se ha configurado TEST_ENV.
        // GitHub Actions validará que la variable exista antes de ejecutar esta prueba.
        if (expectedEnvironment == null || expectedEnvironment.isBlank()) {
            return;
        }

        Assert.assertEquals(
                ConfigReader.getProperty("environment"),
                expectedEnvironment.trim(),
                "ConfigReader should return the TEST_ENV value."
        );
    }

    @Test
    public void shouldFallBackToConfigPropertiesWhenTestEnvIsNotSet() {

        String environmentVariable = System.getenv("TEST_ENV");
        String systemProperty = System.getProperty("TEST_ENV");

        if ((environmentVariable != null && !environmentVariable.isBlank())
                || (systemProperty != null && !systemProperty.isBlank())) {
            return;
        }

        Assert.assertEquals(
                ConfigReader.getProperty("environment"),
                "QA",
                "The environment should fall back to config.properties."
        );
    }
}
