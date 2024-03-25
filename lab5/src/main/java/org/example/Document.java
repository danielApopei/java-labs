package org.example;

/**
 * class that represents a file
 * @param name
 * @param path
 */
public record Document(String name, String path) {


    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
