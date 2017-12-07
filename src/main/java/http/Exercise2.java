package http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Exercise2 {


    public static void main(String[] args) throws IOException {

        System.out.println("Enter the name of the country you wish to know about:");
        Scanner scanner = new Scanner(System.in);       //example: Poland
        String country = scanner.nextLine();
        System.out.println("Looking for data reagrding " + country + "...");

        String url = "http://restcountries.eu/rest/v2/name/" + country;
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("fulltext", "true");

        HTTPMethods httpMethods = new HTTPMethods();
        String json = httpMethods.sendGet(url, requestParameters);

        JSONArray jsonArray = new JSONArray(json);
        JSONObject object = jsonArray.getJSONObject(0);
        System.out.println("Parsing incoming data...");
        printJson(object, 0);



    }
    //Not well formating when dealing with String Arrays in JSONs
    public static void printJson(Object o, int space) {
        if (o instanceof JSONObject) {
            System.out.println();
            JSONObject jsonObject = (JSONObject) o;
            Iterator<String> keys = jsonObject.keys();
            String key;
            Object item;
            while (keys.hasNext()) {
                key = keys.next();
                item = jsonObject.get(key);
                String spaces = String.join("", Collections.nCopies(space, " "));
                System.out.printf(spaces + key + ": ");
                printJson(item, ++space);
                space--;
            }
        } else if (o instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray)o;
            if(jsonArray.length()>0) {
                for (Object arrayItem : jsonArray) {
                    printJson(arrayItem, ++space);
                    space--;
                }
            }else{
                System.out.printf("...empty...\n");
            }
        } else {
            System.out.printf(o.toString() + "\n");
        }
    }

}

