package Database;

import models.Dto.UserDto.CreateUserDto;
import models.User;
import services.UserService;

public class TestConnection {
    public static void main(String[] args) {
        try {
            UserService userService = new UserService();

            CreateUserDto userDto = new CreateUserDto(
                    "Test Përdoruesi",
                    "test@example.com",
                    25,
                    "qytetar" // ose "komunal", "admin"
            );

            User created = userService.create(userDto);
            if (created != null) {
                System.out.println("U shtua me sukses: " + created.getName());
            } else {
                System.out.println("Shtimi dështoi.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

