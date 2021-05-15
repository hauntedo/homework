package darthrusya.lamoda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LamodaProduct {

    List<String> lines;
    ArrayList<String> itemName, itemBrand, itemPrice, itemLink;

    public LamodaProduct(String path) throws IOException {
        this.lines = Files.readAllLines(Paths.get(path));
        this.itemName = new ArrayList<>();
        this.itemBrand = new ArrayList<>();
        this.itemPrice = new ArrayList<>();
        this.itemLink = new ArrayList<>();
    }

    public void execute(String regex, ArrayList<String> arrayList) {
        Pattern p = Pattern.compile(regex);
        for (String s : this.lines) {
            Matcher m = p.matcher(s);
            while (m.find()) {
                arrayList.add(m.group(0));
            }
        }
    }

    public boolean checkSize() throws ArrayListLengthException { //if arraylist's lengths dont equal each other
        if (this.itemName.size() == this.itemBrand.size() &&     //we get exception (dont work :( )
        this.itemLink.size() == this.itemPrice.size() &&
        this.itemName.size() == this.itemLink.size()) {
            return true;
        }
        throw new ArrayListLengthException("something");
    }

    public static void main(String[] args) throws IOException, ArrayListLengthException {
        String nameRegex = "(?<=data-name=\")[\\s]*([А-Яа-яA-Za-z,\\s&;]*)";
        String brandRegex = "(?<=data-brand=\")[\\s]*([А-Яа-яA-Za-z,\\s&;.-]*)";
        String priceRegex = "(?<=data-price-origin=\")[\\s]*([\\d]*)";
        String linkRegex = "(?<=<a href=\")[\\s]*https://www.lamoda.ru/p([^\"]*)";
        LamodaProduct ex = new LamodaProduct("C:\\Users\\Rustem\\Desktop\\lam1.txt");
        ex.execute(nameRegex, ex.itemName);
        ex.execute(brandRegex, ex.itemBrand);
        ex.execute(priceRegex, ex.itemPrice);
        ex.execute(linkRegex, ex.itemLink);
        boolean flag = ex.checkSize();
        for (int i = 0; i < ex.itemName.size() && flag; i++) {
            System.out.printf("%-10s %-17s %-8s %s",
                    ex.itemName.get(i),
                    ex.itemBrand.get(i),
                    ex.itemPrice.get(i),
                    ex.itemLink.get(i));
            System.out.println();
        }
    }

}
