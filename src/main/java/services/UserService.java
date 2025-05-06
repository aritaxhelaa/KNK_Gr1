package services;

import models.User;
import models.Dto.UserDto.CreateUserDto;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid.");
        }

        User user = this.userRepository.getById(id);
        if (user == null) {
            throw new Exception("Përdoruesi me ID " + id + " nuk ekziston.");
        }

        return user;
    }

    public User create(CreateUserDto createUser) throws Exception {
        if (createUser.getAge() <= 12 || createUser.getName().isEmpty()) {
            throw new Exception("Të dhënat nuk janë të vlefshme (emri bosh ose mosha më e vogël se 13).");
        }

        String roli = createUser.getRoli();
        if (roli == null || (!roli.equals("admin") && !roli.equals("komunal") && !roli.equals("qytetar"))) {
            throw new Exception("Roli duhet të jetë një nga: admin, komunal, qytetar.");
        }

        User user = this.userRepository.create(createUser);
        if (user == null) {
            throw new Exception("Krijimi i përdoruesit dështoi.");
        }

        return user;
    }
}
