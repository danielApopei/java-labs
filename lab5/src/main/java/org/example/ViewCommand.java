package org.example;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * command that opens a file in default program
 */
public class ViewCommand implements Command {
    String filepath;

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void execute() {
        Desktop desktop = Desktop.getDesktop();
        File file = Paths.get(filepath).toFile();
        try{
            desktop.open(file);
        }
        catch(Exception e) {
            System.out.println("exception!: ");
        }
    }
}
