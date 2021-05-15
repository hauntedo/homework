package darthrusya.FileManager.Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Commands {

     static class CdCommand implements Command {

        @Override
        public boolean execute(File file) {
            return true;
        }

        @Override
        public String getName() {
            return "cd";
        }

        @Override
        public String getDescription() {
            return "opening the directory, if it is exist";
        } 
    }

    static class LsCommand implements Command {

        @Override
        public boolean execute(File file) {
            for (File f : file.listFiles()) {
                if (f.isDirectory() && !f.isHidden()) {
                    System.out.println("<DIR> " + f.getName());
                } else if (f.isFile() && !f.isHidden()) {
                    System.out.println("<FILE> " + f.getName());
                }
            }
            return true;
        }

        @Override
        public String getName() {
            return "ls";
        }

        @Override
        public String getDescription() {
            return "showing a content of the directory";
        }
    }

    static class TreeCommand implements Command {

        @Override
        public boolean execute(File file) {
            return true;
        }

        @Override
        public String getName() {
            return "tree";
        }

        @Override
        public String getDescription() {
            return "showing the directory as tree";
        }
    }

    static class HelpCommand implements Command {

        @Override
        public boolean execute(File file) {
            CommandProccesor cp = new CommandProccesor();
            ArrayList<Command> helps = cp.getCommands();
            for (Command c : helps) {
                System.out.println(c.getName() + " " + c.getDescription());
            }
            return true;
        }

        @Override
        public String getName() {
            return "help";
        }

        @Override
        public String getDescription() {
            return "command list";
        }
    }

    static class ExitCommand implements Command {

        @Override
        public boolean execute(File file) {
            return false;
        }

        @Override
        public String getName() {
            return "exit";
        }

        @Override
        public String getDescription() {
            return "exit from programm";
        }
    }

}
