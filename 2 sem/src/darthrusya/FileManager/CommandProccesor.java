package darthrusya.FileManager;

import darthrusya.FileManager.Commands.*;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProccesor {

    public Scanner sc;
    private ArrayList<Command> commands;
    private Map<String, Command> com;
    private File currentFile;


    public CommandProccesor() {
        commands = new ArrayList<>();
        sc = new Scanner(System.in);
        com = new HashMap<>();
        commands.add(new CdCommand());
        commands.add(new LsCommand());
        commands.add(new HelpCommand());
        commands.add(new TreeCommand());
        commands.add(new ExitCommand());
        //other commmands
        for (Command c : commands) {
            com.put(c.getName(), c);
        }
    }

    public void init() throws Exception {
        this.currentFile = new File("C:\\");
        String regex = "([a-zA-Z]*)[\\s]*([A-Za-z0-9\\\\]*)";
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
                System.out.println("Not found match");
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
