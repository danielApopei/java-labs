package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * command that generates an HTML file containing a list of all items in repo
 */
public class ReportCommand implements Command{

    private List<Document> documentList = new ArrayList<>();
    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
    public List<String> extractNames() {
        List<String> names = new ArrayList<>();
        for(Document d:documentList) {
            names.add(d.path());
        }
        return names;
    }
    @Override
    public void execute() {
        System.out.println("report!");
        Configuration cfg = new Configuration(new Version("2.3.31"));
        try{
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
            cfg.setDefaultEncoding("UTF-8");
            Template template = cfg.getTemplate("template.ftlh");
            Map<String, Object> root = new HashMap<>();
            root.put("files", extractNames());
            Files.deleteIfExists(Path.of("./files.html"));
            Path outputPath = Files.createFile(Path.of("./files.html"));
            try (Writer fileWriter = new FileWriter(outputPath.toFile())) {
                template.process(root, fileWriter);
            }

            System.out.println("Generated HTML report at: " + outputPath);
            Desktop desktop = Desktop.getDesktop();
            File file = Paths.get("./files.html").toFile();
            try{
                desktop.open(file);
            }
            catch(Exception e) {
                System.out.println("exception!: ");
            }

        } catch(Exception e) {
            System.out.println("exception: " + e);
        }
    }
}
