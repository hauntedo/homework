package darthrusya.FileManager.Commands;

import java.io.File;

public interface Command {

    boolean execute(File file) throws Exception;
    String getName();
    String getDescription();

}
