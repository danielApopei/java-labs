package org.example;

public record Document(String name, String path) {


    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
