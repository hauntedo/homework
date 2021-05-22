package darthrusya.FileManager.Commands;

import java.io.File;

public class ExitCommand implements Command {

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

