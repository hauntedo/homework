package darthrusya.FileManager;

import darthrusya.FileManager.Commands.CommandProccesor;

import java.io.IOException;

public class FileManager {

    public static void main(String[] args) throws Exception {
        CommandProccesor cp = new CommandProccesor();
        cp.init();
    }

}
