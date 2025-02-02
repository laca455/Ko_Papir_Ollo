package com.game.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/jatekospontok";
    private static final String USER = "root";
    private static final String PASSWORD = "MYSQL";

    public SQLDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Hiba: Nem található a MySQL JDBC driver!");
            e.printStackTrace();
        }
    }

    public void mentesAdatbazisba(String player1, String player1Choice, String player2, String player2Choice, String result) {
        String sql = "INSERT INTO jatek_eredmenyek (jatekos1_nev, jatekos1_valasztas, jatekos2_nev, jatekos2_valasztas, eredmeny) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, player1);
            pstmt.setString(2, player1Choice);
            pstmt.setString(3, player2);
            pstmt.setString(4, player2Choice);
            pstmt.setString(5, result);
            pstmt.executeUpdate();
            System.out.println("Sikeres mentés az adatbázisba!");
        } catch (SQLException e) {
            System.out.println("Hiba az adatbázisba való mentéskor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
