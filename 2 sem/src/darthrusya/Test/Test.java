package darthrusya.Test;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        URLConnection conn = new URL("https://www.metaweather.com/api/location/search/?query=" + id).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while((line = r.readLine()) != null){
            sb.append(line);
        }
        Gson gson = new Gson();
        JsonArray data = gson.fromJson(String.valueOf(sb), JsonArray.class);
        for(JsonElement weatherItem : data) {
            JsonObject weatherItemObj = weatherItem.getAsJsonObject();
            System.out.println(weatherItemObj.get("title").getAsString());
            System.out.println(weatherItemObj.get("woeid").getAsString());
        }
    }
}