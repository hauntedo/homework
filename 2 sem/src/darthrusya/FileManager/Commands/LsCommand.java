package darthrusya.FileManager.Commands;

import java.io.File;

public class LsCommand implements Command {

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
