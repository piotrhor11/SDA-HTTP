package commons;

import sun.net.www.content.text.Generic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigReader {

    private static final Logger LOGGER = Logger.getLogger(ConfigReader.class.getName());

    private String configFile;

    public ConfigReader(String configFile) {
        this.configFile = configFile;
    }

    public Integer readPropertie(String property) {          //ToDo Zrobić z tego generyk, żeby zwracał różne typy w zależności od wywołującego

        //Based on http://www.mkyong.com/java/java-properties-file-examples/
        Properties properties = new Properties();
        InputStream inputStream = null;
        Integer value = null;

        LOGGER.info("Reading propriety " + property + " from the file " + configFile);
        try {
            String filename = configFile;
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            inputStream = loader.getResourceAsStream(filename);
            if (inputStream == null) {
                LOGGER.warning("Config file not found, default value for " + property + " will be taken.");
            }
            properties.load(inputStream);                   //Throws IOException

            value = Integer.parseInt(properties.getProperty(property));          //Reading value from config file

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();                    //Throws IOException
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}