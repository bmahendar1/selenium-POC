package config.initialization;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader() {

        String env = System.getProperty("env", "dev"); // Default to 'dev'
        System.out.println("Running tests in env: "+env);
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config-" + env + ".properties")) {
            if (input == null) {
                throw new RuntimeException("Could not find config file for environment: " + env);
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}


