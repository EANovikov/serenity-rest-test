package utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class ConfigReader {
    static Properties prop = new Properties();

    public static String getProperty(String propertyName) {
        try {
            // the configuration file name
            String fileName = "config.properties";
            ClassLoader classLoader = ConfigReader.class.getClassLoader();
            // Make sure that the configuration file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                    "Can't find configuration file app.config");
            InputStream is = new FileInputStream(res.getFile());
            prop.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

}
