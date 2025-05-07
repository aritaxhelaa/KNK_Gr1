package Test;

import Database.DBConnector;
import models.Adresa;
import repository.AdresaRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdresaRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM adresa ORDER BY id DESC LIMIT 1";
            ResultSet result = stmt.executeQuery(query);

            if (result.next()) {
                Adresa a = Adresa.getInstance(result);
                System.out.println("Adresa nga DB: " + a.getRruga() + " - Kodi: " + a.getKodiPostar());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        AdresaRepository repo = new AdresaRepository();
        Adresa a = repo.getById(1);
        if (a != null) {
            System.out.println("Adresa me ID 1: " + a.getRruga());
        }
    }
}
