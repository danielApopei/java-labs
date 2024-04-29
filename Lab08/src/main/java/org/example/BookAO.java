package org.example;

import domain.Book;

import java.sql.*;

public class BookAO {
    public void create(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into books (title) values (?)")) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
        }
    }
    public void create(Book book) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into books (title, language) values (?, ?, ?)")) {
            pstmt.setString(1, book.title);
            pstmt.setString(2, book.language);
            pstmt.setInt(3, book.pageCount);
            pstmt.executeUpdate();
        }
    }
    public Integer findByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from books where title='" + title + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books where id = " + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}

