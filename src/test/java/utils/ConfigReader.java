package utils;

import java.io.IOException;
import java.util.Properties;
import io.github.cdimascio.dotenv.Dotenv;

public class ConfigReader {
    private static Properties properties = new Properties();
    private static final Dotenv dotenv = Dotenv.load();

    static {
        try {
            // Load properties from config.properties file
            properties.load(ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"));

            // Debugging: print the values of the properties loaded (optional)
            System.out.println("Loaded .env values:");
            System.out.println("runMode: " + dotenv.get("runMode"));
            System.out.println("browser: " + dotenv.get("browser"));
            System.out.println("Loaded .env values: " + dotenv.get("LT_USERNAME") + ", " + dotenv.get("LT_ACCESS_KEY"));
        } catch (IOException | NullPointerException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Get property from .env or fallback to config.properties
    public static String getProperty(String key) {
        // Try environment variable first (.env)
        String envValue = dotenv.get(key);
        if (envValue != null) {
            return envValue;
        }

        // Fallback to config.properties
        return properties.getProperty(key);
    }
}
