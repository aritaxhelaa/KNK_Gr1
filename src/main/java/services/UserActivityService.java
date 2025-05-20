package services;

import models.UserActivity;
import models.Dto.UserActivityDto.CreateUserActivityDto;
import models.Dto.UserActivityDto.UpdateUserActivityDto;
import repository.UserActivityRepository;

import java.util.List;

public class UserActivityService {
    private final UserActivityRepository repository;

    public UserActivityService() {
        this.repository = new UserActivityRepository();
    }

    public UserActivity create(CreateUserActivityDto dto) {
        return repository.create(dto);
    }

    public UserActivity update(UpdateUserActivityDto dto) {
        return repository.update(dto);
    }

    public List<UserActivity> findAll() {
        return repository.getAll();
    }

    public UserActivity getById(int id) {
        return repository.getById(id);
    }

    public List<UserActivity> getByUserId(int userId) {
        return repository.getByUserId(userId);
    }

}
