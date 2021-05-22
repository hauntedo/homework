package darthrusya.FileManager.Commands;

import darthrusya.FileManager.CommandProccesor;

import java.io.File;
import java.util.ArrayList;

public class HelpCommand implements Command {

    @Override
    public boolean execute(File file) {
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
