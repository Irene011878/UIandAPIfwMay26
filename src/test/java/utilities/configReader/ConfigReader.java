package utilities.configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public final class ConfigReader {

    private static final String CONFIG_FILE_PATH =
            "src/test/resources/properties/config.properties";

    private static final Properties properties = new Properties();

    static {

        try (FileInputStream file = new FileInputStream(CONFIG_FILE_PATH)) {

            properties.load(file);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load configuration file: " + CONFIG_FILE_PATH, e);

        }

    }

    private ConfigReader() {

        throw new UnsupportedOperationException(
                "Utility class should not be instantiated.");
    }

    public static String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null || value.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Property '" + key + "' was not found in " + CONFIG_FILE_PATH
            );

        }

        return value.trim();

    }

}
