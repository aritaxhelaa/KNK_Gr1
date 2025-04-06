package com.example.repository;

import com.example.database.DBConnection;
import com.example.models.Komuna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KomunaRepository {

    public List<Komuna> getAll() {
        List<Komuna> komunat = new ArrayList<>();

        String sql = "SELECT * FROM komunat";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Komuna k = new Komuna(
                        rs.getInt("id"),
                        rs.getString("emri"),
                        rs.getString("kodi_postar")
                );
                komunat.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return komunat;
    }
    public void addKomuna(Komuna komunë) {
        String sql = "INSERT INTO komunat (emri, kodi_postar) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, komunë.getEmri());
            pstmt.setString(2, komunë.getKodiPostar());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
