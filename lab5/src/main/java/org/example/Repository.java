package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class that manages the files inside a master directory
 */
public class Repository {
    private String directory;
    private List<Document> documentList = new ArrayList<>();
    public Repository(String directory) {
        this.directory = directory;
        loadDocuments();
    }

    void loadDocuments() {
        try {
            // Use try-with-resources to ensure the stream is closed
            try (var paths = Files.walk(Path.of(this.directory))) {
                documentList = paths
                        .filter(Files::isRegularFile) // Filter only files, exclude directories
                        .map(path -> new Document(path.getFileName().toString(), path.toString())) // Map each Path to a Document
                        .collect(Collectors.toList()); // Collect results into a list
            }
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void displayRepositoryContent() {
        if (documentList.isEmpty()) {
            System.out.println("The repository is empty.");
            return;
        }
        documentList.forEach(System.out::println);
    }
}
