package darthrusya.FileManager.Commands;

import java.io.File;

public interface Command {

    boolean execute(File file);
    String getName();
    String getDescription();

}
