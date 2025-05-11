package repository;

import models.Dto.UserActivityDto.CreateUserActivityDto;
import models.Dto.UserActivityDto.UpdateUserActivityDto;
import models.UserActivity;

import java.sql.*;

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
            INSERT INTO user_activity (user_id, veprimi, entiteti, entiteti_id, data)
            VALUES (?, ?, ?, ?, ?)
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getUserId());
            pstm.setString(2, dto.getVeprimi());
            pstm.setString(3, dto.getEntiteti());
            pstm.setInt(4, dto.getEntitetiId());
            pstm.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // auto-generated time
            pstm.execute();

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
        SET veprimi = ?, entiteti = ?, entiteti_id = ?
        WHERE id = ?
    """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getVeprimi());
            pstm.setString(2, dto.getEntiteti());
            pstm.setInt(3, dto.getEntitetiId());
            pstm.setInt(4, dto.getId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return getById(dto.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
