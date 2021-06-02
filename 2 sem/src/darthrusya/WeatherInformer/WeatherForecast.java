package darthrusya.WeatherInformer;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WeatherForecast implements Serializable {

    public static final String LOCATION_SEARCH_URL = "https://www.metaweather.com/api/location/search/?query=";
    public static final String LOCATION_ID_URL = "https://www.metaweather.com/api/location/";


    public Scanner sc;
    public Gson gson;
    private String id;
    private Map<String, String> idList;

    public WeatherForecast() {
        idList = new TreeMap<>();
        sc = new Scanner(System.in);
        gson = new Gson();
    }

    public void execute() throws IOException {
        boolean flag = true;
        while (flag) {
            String s = sc.nextLine();
            if (s.toLowerCase().equals("exit")) {
                flag = false;
            }
            else {
                identifyID(s);
                if (this.idList.size() != 1) {
                    System.out.println("Specify the city");
                    System.out.println(this.idList.values());
                    String city = sc.nextLine();
                    getLocationWeather(city);
                } else {
                    getLocationWeather(s);
                }
            }
        }
    }

    public String getLocationInfo(String s, String URL) throws IOException {
        URLConnection conn = new URL(URL + s).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while((line = r.readLine()) != null){
            sb.append(line);
        }
        return String.valueOf(sb);
    }

    public void identifyID(String s) throws IOException {
        JsonArray data = gson.fromJson(String.valueOf(getLocationInfo(s, LOCATION_SEARCH_URL)), JsonArray.class);
        for(JsonElement cityItem : data) {
            JsonObject cityItemObj = cityItem.getAsJsonObject();
            this.idList.put(cityItemObj.get("woeid").getAsString(), cityItemObj.get("title").getAsString());
        }
    }

    public void getLocationWeather(String s) throws IOException {
        for (Map.Entry<String, String> pair : this.idList.entrySet()) {
            if (pair.getValue().toLowerCase().equals(s.toLowerCase())) {
                JsonObject data = gson.fromJson(getLocationInfo(pair.getKey().toLowerCase(), LOCATION_ID_URL), JsonObject.class);
                JsonArray weatherData = data.getAsJsonArray("consolidated_weather");
                System.out.println(pair.getValue());
                for(JsonElement weatherItem : weatherData) {
                    JsonObject weatherItemObj = weatherItem.getAsJsonObject();
                    System.out.println(weatherItemObj.get("applicable_date").getAsString());
                    System.out.print("Temp: ");
                    System.out.printf("%.1f", weatherItemObj.get("min_temp").getAsDouble());
                    System.out.print(" - ");
                    System.out.printf("%.1f%n", weatherItemObj.get("max_temp").getAsDouble());
                }
            }
        }
        this.idList.clear();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
