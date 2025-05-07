package Test;

import Database.DBConnector;
import models.KodiPostar;
import repository.KodiPostarRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class KodiPostarRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM kodi_postar ORDER BY id DESC LIMIT 1";
            ResultSet result = stmt.executeQuery(query);

            if (result.next()) {
                KodiPostar k = KodiPostar.getInstance(result);
                System.out.println("Kodi: " + k.getKodi() + ", Regjioni: " + k.getRegjioni());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        KodiPostarRepository repo = new KodiPostarRepository();
        KodiPostar k = repo.getById(1);
        if (k != null) {
            System.out.println("Kodi me ID 1: " + k.getKodi());
        }
    }
}
