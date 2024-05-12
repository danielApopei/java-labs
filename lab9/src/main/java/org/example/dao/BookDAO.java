package org.example.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Book;
import org.example.config.Database;

import java.sql.*;

public class BookDAO {
    public static void create(Book book) throws SQLException, JsonProcessingException {
        Connection con = Database.getConnection();

        ObjectMapper objectMapper = new ObjectMapper();
        String authorsToJson = objectMapper.writeValueAsString(book.getAuthor());


        String sql = "insert into books (title, authors, pages, publication_date) values (?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, authorsToJson);
        statement.setInt(3, book.getPages());
        statement.setDate(4, new Date(book.getPublicationDate().getTime()));
        statement.executeUpdate();
        statement.close();

        for(int i=0; i<book.getAuthor().size(); i++) {
            if (AuthorDAO.findByName(book.getAuthor().get(i)) == null) {
                AuthorDAO.create(book.getAuthor().get(i));
            }
        }
        con.close();
    }
    public static Integer findByName(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from books where title='" + title + "'")) {
            while (rs.next()) {
                String selectedTitle = rs.getString("title");
                // Process each row of data
                System.out.println("Title: " + title);
            }
            return rs.next() ? rs.getInt(1) : null;
        }
    }
}
