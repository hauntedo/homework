package darthrusya.FileManager.Commands;

import java.io.File;

public class TreeCommand implements Command {

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
