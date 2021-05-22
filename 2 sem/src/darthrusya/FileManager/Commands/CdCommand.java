package darthrusya.FileManager.Commands;

import darthrusya.FileManager.CommandProccesor;

import java.io.File;
import java.util.Scanner;

public class CdCommand implements Command  {

    @Override
    public boolean execute(File file) {
        CommandProccesor cp = new CommandProccesor();
        return true;
    }

    @Override
    public String getName() {
        return "cd";
    }

    @Override
    public String getDescription() {
        return "open the specified directory";
    }

}
