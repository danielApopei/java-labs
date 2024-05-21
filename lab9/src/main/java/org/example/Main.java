package org.example;

import org.example.config.Database;
import org.example.dao.AuthorDAO;
import org.example.models.Book;
import org.example.repositories.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Database.createConnection();
        AuthorDAO authors = new AuthorDAO();

        BookRepository bookRepository = new BookRepository();
        Book book = new Book("Title", 350, new Date());
        bookRepository.create(book);

        String persistenceUnitName = "YourPersistenceUnit";




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

//        try {
//            Integer foundById = BookDAO.findByName("Book Title");
//            System.out.println(foundById);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try{
//            DataImportTool dit = new DataImportTool();
//            dit.readCSV("./datasets/books.csv/books.csv");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        // EclipseLink JPA manipulation
        testJPA();
    }

    static void testJPA() {
        //EntityManager em = JPAEntityManagerFactory.createEntityManager();
        System.out.println("Test complete!");
    }
}