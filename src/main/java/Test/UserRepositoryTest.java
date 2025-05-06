package Test;

import Database.DBConnector;
import models.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM users ORDER BY id DESC LIMIT 1";
            ResultSet result = stmt.executeQuery(query);

            if (result.next()) {
                User u = User.getInstance(result);
                System.out.println("User: " + u.getName() + " - " + u.getEmail());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test direkt për getById
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getById(1);
        if (user != null) {
            System.out.println("Nga ID 1: " + user.getName());
        }
    }
}
