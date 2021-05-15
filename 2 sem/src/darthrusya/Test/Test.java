package darthrusya.Test;

import javax.print.attribute.standard.PrinterURI;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("C:\\Users\\Rustem\\Desktop\\abc.txt");
//        String name = String.valueOf((char) fis.read());
//        for(int i = 1; i < 5; i++){
//            name += (char) fis.read();
//        }
//        System.out.println(name);
//        Product cola = new Product(name);
//        fis.close();
        Scanner sc = new Scanner(System.in);
        String city = sc.nextLine();
        URLConnection conn = new URL("https://www.metaweather.com/api/location/search/?query=" + city).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }
        String regex = "(?<=\"woeid\":)([0-9]*)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sb);
        String id = null;
        if (m.find()) {
            id = m.group(1);
        }
        System.out.println(id);
        URLConnection conn1 = new URL("https://www.metaweather.com/api/location/" + id + "/").openConnection();
        BufferedReader r1 = new BufferedReader(new InputStreamReader(conn1.getInputStream(), "UTF-8"));
        StringBuffer sb1 = new StringBuffer();
        String line1 = null;
        while ((line1 = r1.readLine()) != null) {
            sb1.append(line1);
        }
        String regex1 = "(?<=\"the_temp\":)([0-9.]*)";
        Pattern p1 = Pattern.compile(regex1);
        Matcher m1 = p1.matcher(sb1);
        String temp = null;
        if (m1.find()) {
            temp = m1.group(1);
        }
        System.out.println(temp);

    }
}

class Product implements Serializable {

    private String name;
    private double price;
    private int num;

    public Product(String name, double price, int num) throws IOException {
        setName(name);
        this.price = price;
        this.num = num;
    }

    public Product(String name) throws IOException {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IOException {
        if (name.length() == 5) {
            this.name = name;
        } else {
            throw new IOException("нет 5 символов");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                num == product.num &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, num);
    }
}
