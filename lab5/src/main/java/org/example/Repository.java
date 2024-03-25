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
    private List<Person> personList = new ArrayList<>();
    public List<Document> getDocumentList () {
        return this.documentList;
    }
    public Repository(String directory) {
        this.directory = directory;
        loadDocuments();
    }

    void loadDocuments() {
        try {
            try (var paths = Files.walk(Path.of(this.directory))) {
                documentList = paths
                        .map(path -> new Document(path.getFileName().toString(), path.toString())) // Map each Path to a Document
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        for(Document d: documentList) {
            if(Files.isDirectory(Path.of(d.path())) && d.name().matches("[A-Za-z]+_[0-9]+")){
                personList.add(new Person(Integer.parseInt(d.name().split("_")[1]),d.name().split("_")[0]));
            }
        }
    }

    public void displayRepositoryContent() {
        if (documentList.isEmpty()) {
            System.out.println("The repository is empty.");
            return;
        }
        System.out.println("Documents: ");
        documentList.forEach(System.out::println);
        System.out.println("Person: ");
        personList.forEach(System.out::println);
    }

    public List<Person> getPersonList() {
        return getPersonList();
    }

    public String getDirectory() {
        return directory;
    }
}
