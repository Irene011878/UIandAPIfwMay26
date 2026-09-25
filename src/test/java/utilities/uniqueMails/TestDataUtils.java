package utilities.uniqueMails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public final class TestDataUtils {

    private static final DateTimeFormatter EMAIL_TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private TestDataUtils() {
        // Prevent instantiation
    }

    // Returns a copy of the original test data with a unique email.
    public static Map<String, String> generateUniqueUser(
            Map<String, String> data) {

        if (data == null) {

            throw new IllegalArgumentException(
                    "Test data cannot be null.");

        }

        String email = data.get("email");

        if (email == null || email.isBlank()) {

            throw new IllegalArgumentException(
                    "Email field is missing or empty.");

        }

        Map<String, String> user = new HashMap<>(data);

        user.put(
                "email",
                generateUniqueEmail(email));

        return user;

    }

    // Generates a unique email while preserving the original domain.
    private static String generateUniqueEmail(String baseEmail) {

        String[] parts = baseEmail.split("@");

        if (parts.length != 2) {

            throw new IllegalArgumentException(
                    "Invalid email format: " + baseEmail);

        }

        String username = parts[0];
        String domain = parts[1];

        String emailTimestamp =
                LocalDateTime.now()
                        .format(EMAIL_TIMESTAMP_FORMAT);

        return username + "_" + emailTimestamp + "@" + domain;

    }

}