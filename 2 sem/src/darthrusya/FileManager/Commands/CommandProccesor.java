package darthrusya.FileManager.Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProccesor {

    private ArrayList<Command> commands;

    private Map<String, Command> com;

    private File currentFile;
    public CommandProccesor() {
        commands = new ArrayList<>();
        com = new TreeMap<>();
        commands.add(new Commands.CdCommand());
        commands.add(new Commands.LsCommand());
        commands.add(new Commands.HelpCommand());
        commands.add(new Commands.TreeCommand());
        commands.add(new Commands.ExitCommand());
        //other commmands
        for (Command c : commands) {
            com.put(c.getName(), c);
        }
    }

    public void init() throws Exception {
        Scanner sc = new Scanner(System.in);
        String regex = "([a-zA-Z]*)[\\s]*([A-Za-z0-9\\\\]*)";
        this.currentFile = new File("C:\\");
        boolean result = true;
        while(result) {
            String line = sc.nextLine();
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            try {
                if (m.find()) {
                    result = com.get(m.group(1)).execute(this.currentFile);
                }
            } catch(Exception ex) {
                System.out.println("Not found match");;
            }
        }

    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public ArrayList<Command> getCommands(){
        return commands;
    }

    public Map<String, Command> getCom() {
        return com;
    }

    public void setCom(Map<String, Command> com) {
        this.com = com;
    }

}
