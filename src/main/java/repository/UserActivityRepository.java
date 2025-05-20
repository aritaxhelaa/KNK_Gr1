package repository;

import models.Dto.UserActivityDto.CreateUserActivityDto;
import models.Dto.UserActivityDto.UpdateUserActivityDto;
import models.UserActivity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserActivityRepository extends BaseRepository<UserActivity, CreateUserActivityDto, UpdateUserActivityDto> {
    public UserActivityRepository() {
        super("user_activity");
    }

    @Override
    public UserActivity fromResultSet(ResultSet resultSet) throws SQLException {
        return UserActivity.getInstance(resultSet);
    }

    @Override
    public UserActivity create(CreateUserActivityDto dto) {
        String query = """
            INSERT INTO user_activity (user_id, data, adresa)
            VALUES (?, ?, ?)
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getUserId());
            pstm.setString(2, dto.getData());
            pstm.setString(3, dto.getAdresa());
            pstm.executeUpdate();

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                return getById(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UserActivity update(UpdateUserActivityDto dto) {
        String query = """
            UPDATE user_activity
            SET data = ?, adresa = ?
            WHERE id = ?
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getData());
            pstm.setString(2, dto.getAdresa());
            pstm.setInt(3, dto.getId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return getById(dto.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<UserActivity> getByUserId(int userId) {
        List<UserActivity> result = new ArrayList<>();
        String query = "SELECT * FROM user_activity WHERE user_id = ?";

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                result.add(UserActivity.getInstance(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
