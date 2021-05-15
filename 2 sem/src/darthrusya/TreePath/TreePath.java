package darthrusya.TreePath;

import java.io.File;

public class TreePath {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Rustem\\Desktop\\JavaTest\\KFUITIS_2semester");
        showDirectory(file, 0);
    }

    public static void showDirectory(File file, Integer d) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_YELLOW = "\u001B[33m";
        for (File item : file.listFiles()) {
            if (item.isDirectory()) {
                System.out.println(stringMultiply(" ", d*2) + "|--" + ANSI_BLUE + item.getName() + ANSI_RESET);
                d+=2;
                showDirectory(item, d);
                d-=2;
            }
            else if (item.isFile()) {
                System.out.println(stringMultiply(" ", d*2) + "|--" + ANSI_YELLOW + item.getName() + ANSI_RESET);
            }
        }
    }

    public static String stringMultiply(String s, int n){
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(s).repeat(Math.max(0, n)));
        return sb.toString();
    }

}
