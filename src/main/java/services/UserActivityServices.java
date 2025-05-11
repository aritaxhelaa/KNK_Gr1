package services;

import models.UserActivity;
import models.Dto.UserActivityDto.CreateUserActivityDto;
import models.Dto.UserActivityDto.UpdateUserActivityDto;
import repository.UserActivityRepository;

public class UserActivityServices {
    private final UserActivityRepository userActivityRepository;

    public UserActivityServices() {
        this.userActivityRepository = new UserActivityRepository();
    }

    public UserActivity getById(int id) throws Exception {
        if (id <= 0) throw new Exception("ID nuk është valid!");
        UserActivity log = userActivityRepository.getById(id);
        if (log == null) throw new Exception("Log me ID " + id + " nuk ekziston.");
        return log;
    }

    public UserActivity create(CreateUserActivityDto dto) throws Exception {
        if (dto.getUserId() <= 0 || dto.getEntitetiId() <= 0 || dto.getVeprimi() == null || dto.getEntiteti() == null) {
            throw new Exception("Të dhënat për logim nuk janë valide.");
        }
        UserActivity log = userActivityRepository.create(dto);
        if (log == null) throw new Exception("Logu nuk u regjistrua.");
        return log;
    }

    public UserActivity update(UpdateUserActivityDto dto) throws Exception {
        if (dto.getId() <= 0) throw new Exception("ID për përditësim nuk është valid.");
        UserActivity log = userActivityRepository.update(dto);
        if (log == null) throw new Exception("Përditësimi i logut dështoi.");
        return log;
    }

    public void delete(int id) throws Exception {
        if (id <= 0) throw new Exception("ID për fshirje nuk është valid.");
        userActivityRepository.delete(id);
    }

}
