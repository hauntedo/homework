package darthrusya.CocktailDB;

import com.google.gson.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Cocktail {

    public static final String COCKTAIL_URL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";

    public Scanner sc;
    public Gson gson;

    private String name;
    private String imageLink;

    private ArrayList<String> ingList;;
//подразумевалось, что мы будем хранить ингредиенты в ArrayList, а затем передадим их в HashMap для хранения...
//названия коктейля и, соответственно, ингредиентов, но что-то пошло не так..
    public Cocktail() {
        ingList = new ArrayList<>();
        sc = new Scanner(System.in);
        gson = new Gson();
    }

    public void execute() throws IOException {
        boolean flag = true;
        while (flag) {
            String s = sc.nextLine();
            if (s.toLowerCase().equals("exit")) {
                flag = false;
            } else {
                getCocktailDetails(s);
            }
         }
    }

    public String getJSON(String name) throws IOException {
        URLConnection conn = new URL(COCKTAIL_URL + name).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }
        return String.valueOf(sb);
    }

    public void getCocktailDetails(String name) throws IOException {
        boolean flag = true;
        JsonObject data = gson.fromJson(getJSON(name), JsonObject.class);
        JsonArray cocktailData = data.getAsJsonArray("drinks");
        for (JsonElement js : cocktailData) {
            JsonObject cockTailItemObj = js.getAsJsonObject();
            try {
                for (int i = 1; i < 16 && flag; i++) {
                    this.ingList.add(cockTailItemObj.get("strIngredient" + String.valueOf(i)).getAsString());
                }
            } catch(UnsupportedOperationException ex) {
                flag = false;
            }
            this.name = cockTailItemObj.get("strDrink").getAsString();
            this.imageLink = cockTailItemObj.get("strDrinkThumb").getAsString();
            System.out.println(this.name);
            System.out.println(this.ingList.toString());
            System.out.println(this.imageLink);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
