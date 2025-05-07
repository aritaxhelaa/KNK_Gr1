package Test;

import Database.DBConnector;
import models.User;
import models.Dto.UserDto.UpdateUserDto;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        try {
            Statement stm = connection.createStatement();
            String query = "SELECT * FROM users ORDER BY id DESC LIMIT 1";
            ResultSet result = stm.executeQuery(query);
            if (result.next()) {
                User user = User.getInstance(result);
                System.out.println("ID: " + user.getId());
                System.out.println("Email: " + user.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserRepository userRepository = new UserRepository();
        User user = userRepository.getById(1);
        if (user != null) {
            System.out.println("Emri: " + user.getName());
        }

        // Test përditësim
//        UpdateUserDto updateUser = new UpdateUserDto();
//        updateUser.setId(1);
//        updateUser.setEmail("updated.email@gmail.com");
//        userRepository.update(updateUser);

        // Test fshirje
//        userRepository.delete(3);
    }
}
