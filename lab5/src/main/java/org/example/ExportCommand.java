package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * command that exports a JSON file with all items in repo
 */
public class ExportCommand implements Command {
    private final List<Document> documentList;

    public ExportCommand(List<Document> documentList) {
        this.documentList = documentList;
    }

    @Override
    public void execute() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Files.deleteIfExists(Path.of("./myfile.json"));
            Path outputPath = Files.createFile(Path.of("./myfile.json"));

//             If you want to pretty print the JSON output
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputPath.toFile(), documentList);

            System.out.println("Repository exported to JSON at: " + outputPath);
            Desktop desktop = Desktop.getDesktop();
            File file = Paths.get("./myfile.json").toFile();
            try{
                desktop.open(file);
            }
            catch(Exception e) {
                System.out.println("exception!: ");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while exporting the repository: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
