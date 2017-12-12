package commons;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

public class CryptoCurrencyObjectMapper {

    private static final Logger LOGGER = Logger.getLogger(CryptoCurrencyObjectMapper.class.getName());

    public static CryptoCurrencyDTO parseJsonToCryptoCurrencyDTO(String jsonObject){

        LOGGER.info("Mapping json to CryptoCurrencyDTO...");

        ObjectMapper objectMapper = new ObjectMapper();
        CryptoCurrencyDTO cryptoCurrencyDTO = new CryptoCurrencyDTO();

        try {
            cryptoCurrencyDTO = objectMapper.readValue(jsonObject, CryptoCurrencyDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cryptoCurrencyDTO;
    }

}
