package http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public class Exercise3 {

    private static final Logger LOGGER = Logger.getLogger(Exercise3.class.getName());

    public static void main(String[] args) {

        String url = "https://api.coinmarketcap.com/v1/ticker/";
        Integer limit = 3;          //default value

        LOGGER.info("Reading proprieties from file");

        //Based on http://www.mkyong.com/java/java-properties-file-examples/
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            String filename = "config.properties";
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            inputStream = loader.getResourceAsStream(filename);
            if (inputStream == null) {
                LOGGER.warning("Config file not found, default value for limit (" + limit + ") will be taken.");
            }
            properties.load(inputStream);                   //Throws IOException

            limit = Integer.parseInt(properties.getProperty("limit"));          //Reading value from config file

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

        LOGGER.info("Preparing proper URL request...");

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("limit", limit.toString());

        HTTPMethods httpMethods = new HTTPMethods();

        LOGGER.info("Downloading names of first " + limit + " virtual currencies...");

        String response = null;

        try {
            response = httpMethods.sendGet(url, requestParameters);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
