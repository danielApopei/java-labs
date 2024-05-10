package org.example.dao;

import org.example.config.Database;

import java.sql.*;

public class AuthorDAO {
    public static void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into authors (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
        con.close();
    }
    public static Integer findByName(String name) throws SQLException {
        String sql = "select * from authors where name = ?";
        try (Connection con = Database.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name1 = rs.getString("name");
                    // System.out.println("ID: " + id + ", Name: " + name1);
                    return id;
                } else {
                    return null;
                }
            }
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from authors where id = " + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}

