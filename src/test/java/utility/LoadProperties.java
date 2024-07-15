package utility;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class LoadProperties {
    private static final String PROPERTIES_FILE = "/application.properties";
    private static final Properties PROPERTIES = getPropertiesInstance();

    public static String loadProperty(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }

    @SneakyThrows
    private static Properties getPropertiesInstance() {
        Properties instance = new Properties();
//        try {

            @Cleanup InputStream resource = LoadProperties.class.getResourceAsStream(PROPERTIES_FILE);
            @Cleanup InputStreamReader inputStream = new InputStreamReader(Objects.requireNonNull(resource), StandardCharsets.UTF_8);
            instance.load(inputStream);

//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
        return instance;
    }
}
