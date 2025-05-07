package Test;

import models.User;
import models.Dto.UserDto.CreateUserDto;
import models.Dto.UserDto.UpdateUserDto;
import services.UserService;

public class UserServiceTest {
    public static void main(String[] args){
        UserService userService = new UserService();

        try {
            // Testo leximin e një përdoruesi
            User user = userService.getById(1);
            System.out.println("Emri: " + user.getName());

            // Testo krijimin e një përdoruesi të ri
//            CreateUserDto createUser = new CreateUserDto("Arben", "arben@gmail.com", 25, "qytetar");
//            userService.create(createUser);

            // Testo përditësimin e një përdoruesi
//            UpdateUserDto updateUser = new UpdateUserDto();
//            updateUser.setId(1);
//            updateUser.setEmail("arben_updated@gmail.com");
//            userService.update(updateUser);

            // Testo fshirjen
//            userService.delete(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
