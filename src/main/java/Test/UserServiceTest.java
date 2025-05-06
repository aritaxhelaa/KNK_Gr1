package Test;

import models.User;
import models.Dto.UserDto.CreateUserDto;
import repository.UserRepository;
import services.UserService;

public class UserServiceTest {
    public static void main(String[] args) {
        UserService userService = new UserService();

        try {

            CreateUserDto dto = new CreateUserDto("TestUser", "testuser@example.com", 24, "qytetar");
            User u = userService.create(dto);
            System.out.println("User i krijuar: " + u);


            User fromDb = userService.getById(u.getId());
            System.out.println("Nga DB: " + fromDb.getName() + ", " + fromDb.getEmail());

            // userService.delete(u.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
