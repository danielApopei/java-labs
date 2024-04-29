package org.example;

import java.sql.*;

public class ArtistAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into artists values(?,?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from artists where name = '" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(Integer id) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from artists where id = " + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
