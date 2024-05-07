package org.example;

import org.example.config.Database;
import org.example.dao.AuthorDAO;
import org.example.dao.BookDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Database.createConnection();
        AuthorDAO authors = new AuthorDAO();
//        try {
//            authors.create("David Blaine");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Integer foundById = authors.findByName("David Blaine");
//            System.out.println(foundById);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        Book book = new Book("Book Title", List.of("David Blaine", "Author 2"), 100, new java.util.Date());
//        try {
//            BookDAO.create(book);
//        } catch (SQLException | JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

        try {
            Integer foundById = BookDAO.findByName("Book Title");
            System.out.println(foundById);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}