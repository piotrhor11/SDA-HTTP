package http;

import commons.ConfigReader;
import commons.CryptoCurrencyDTO;
import commons.CryptoCurrencyObjectMapper;
import commons.HTTPMethods;
import javaFx.MainJavaFx;
import javafx.application.Application;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

public class Exercise3 {

    private static final Logger LOGGER = Logger.getLogger(Exercise3.class.getName());

    //Declared static because we need to get it to populate data in ChoiceBox in JavaFx controller
    private static HashSet<CryptoCurrencyDTO> cryptoCurrencyDTOHashSet = new HashSet<>();

    public static HashSet<CryptoCurrencyDTO> getCryptoCurrencyDTOHashSet() {
        return cryptoCurrencyDTOHashSet;
    }

    public static void main(String[] args) {

        String url = "https://api.coinmarketcap.com/v1/ticker/";
        Integer limit = 3;          //default value

        LOGGER.info("Reading proprieties from file");
        ConfigReader configReader = new ConfigReader("config.properties");
        limit = configReader.readPropertie("limit");

        LOGGER.info("Preparing proper URL request...");
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("limit", limit.toString());

        LOGGER.info("Downloading names of first " + limit + " virtual currencies...");
        HTTPMethods httpMethods = new HTTPMethods();
        String response = null;
        try {
            response = httpMethods.sendGet(url, requestParameters);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(response);
        CryptoCurrencyDTO cryptoCurrencyDTO;

        for (Object jsonObject: jsonArray) {
            if (jsonObject instanceof JSONObject){
                cryptoCurrencyDTO = CryptoCurrencyObjectMapper.parseJsonToCryptoCurrencyDTO(jsonObject.toString());
                cryptoCurrencyDTOHashSet.add(cryptoCurrencyDTO);
            }
        }

//        for (CryptoCurrencyDTO ccDTO: cryptoCurrencyDTOHashSet) {
//            System.out.println(ccDTO.getName() + " price in USD: " + ccDTO.getPrice_usd());
//        }

        Application.launch(MainJavaFx.class);

    }

}
