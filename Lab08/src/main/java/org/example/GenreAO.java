package org.example;

import domain.Author;
import domain.Genre;

import java.sql.*;

public class GenreAO {
    public void create(String genreName) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (genreName) values (?)")) {
            pstmt.setString(1, genreName);
            pstmt.executeUpdate();
        }
    }
    public void create(Genre genre) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (genreName) values (?)")) {
            pstmt.setString(1, genre.genreName);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from genres where genreName='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from genres where id = " + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}

