package http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Exercise1 {

    public static void main(String[] args) throws Exception {

        String json;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://restcountries.eu/rest/v2/lang/pl?fulltext=true");
        CloseableHttpResponse response1 = httpclient.execute(httpGet);

        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();

            json = IOUtils.toString(entity1.getContent());
//            json = entity1.getContent().toString();   //to samo

            EntityUtils.consume(entity1);
        } finally {
            response1.close();
        }

        JSONArray array = new JSONArray(json);
        JSONObject obj = array.getJSONObject(0);

        String name = obj.get("name").toString();
        System.out.println("Name: "+ name);

        double area = Double.parseDouble(obj.get("area").toString());
        System.out.println("Area of " + name + " is " + area);

        long population = Long.parseLong(obj.get("population").toString());
        System.out.println("Population of " + name + " is " + population);

        String capital = obj.get("capital").toString();
        System.out.println("Capital of " + name + " is " + capital);

        JSONObject languageObj = obj.getJSONArray("languages").getJSONObject(0);
        String language = languageObj.get("name").toString();
        System.out.println("Language: "+ language);

        JSONObject translationObj = obj.getJSONObject("translations");
        String translation = translationObj.getString("ja");
        System.out.println("Translation to jp: " + translation);

        String borders = obj.get("borders").toString();
        System.out.println("Borders: " + borders);

//        JSONObject bordersObj = obj.getJSONObject("borders");
//        List<String> bordersList = new ArrayList<String>();
//        for (Object country : obj.get("borders")) {

    }

}
