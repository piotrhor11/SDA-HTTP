package http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class HTTPMethods {

    private final String USER_AGENT = "Mozilla/5.0";

    //HTTP GET
    public String sendGet(String url, HashMap<String, String> requestParameters) throws IOException {
    //Note: We take care of getInputStream() method IOException, other methods' exceptions we push back to invoker
        String completeUrl = urlBuilder(url, requestParameters);
        URL objUrl = new URL(completeUrl);
        HttpURLConnection connection = (HttpURLConnection) objUrl.openConnection();

        connection.setRequestMethod("GET");                         //GET is default, by anyway repeated to remember
        connection.setRequestProperty("User-Agent", USER_AGENT);    //Header added

        System.out.println("Sending 'GET' request on " + completeUrl);
        int responseCode = connection.getResponseCode();
        System.out.println("Response code is " + responseCode);

        StringBuffer response = new StringBuffer();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine())!=null){
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Cannot download data from given resource!");
//            e.printStackTrace();
            return null;
        }

        return response.toString();
    }

    private String urlBuilder(String url, HashMap<String, String> requestParameters) {

        StringBuilder urlSB = new StringBuilder(url);

        if(requestParameters.size()>0){
            urlSB.append("?");
            Integer size = requestParameters.size();
            Integer currentIteration = 0;
            for (HashMap.Entry<String, String> parameter: requestParameters.entrySet()) {
                urlSB.append(parameter.getKey());
                currentIteration++;
                if (!parameter.getValue().equals("") || parameter.getValue()!=null){
                    urlSB.append("=");
                    urlSB.append(parameter.getValue());
                }
                if (currentIteration<size)      //A way to find the last iteration, where we won't append "&"
                urlSB.append("&");
            }
        }
        return urlSB.toString();
    }










}
