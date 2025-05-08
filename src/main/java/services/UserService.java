package services;

import models.User;
import models.Dto.UserDto.CreateUserDto;
import models.Dto.UserDto.LoginUserDto;
import repository.UserRepository;
import util.PasswordHasher;

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

    public User login(LoginUserDto loginDto) throws Exception {
        if (loginDto.getEmail().isEmpty() || loginDto.getPassword().isEmpty()) {
            throw new Exception("Email dhe fjalëkalimi nuk mund të jenë bosh.");
        }

        User user = this.userRepository.getByEmail(loginDto.getEmail());
        if (user == null) {
            throw new Exception("Përdoruesi nuk ekziston.");
        }

        boolean passwordValid = PasswordHasher.compareSaltedHash(
                loginDto.getPassword(),
                user.getSalt(),
                user.getPasswordHash()
        );

        if (!passwordValid) {
            throw new Exception("Fjalëkalimi është i pasaktë.");
        }

        return user;
    }
}
