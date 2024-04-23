package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ArtistAO authors = new ArtistAO();
            authors.create("David Blaine");
            Database.getConnection().commit();
            Database.getConnection().close();
        } catch (SQLException e) {
            Database.rollback();
            System.err.println(e.getMessage());
        }
    }
}