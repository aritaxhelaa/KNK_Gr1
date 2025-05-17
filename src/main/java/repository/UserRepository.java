package repository;

import Database.DBConnector;
import models.User;
import models.Dto.UserDto.CreateUserDto;
import models.Dto.UserDto.UpdateUserDto;

import java.sql.*;

public class UserRepository extends BaseRepository<User, CreateUserDto, UpdateUserDto> {

    public UserRepository() {
        super("users");
    }

    @Override
    public User fromResultSet(ResultSet result) throws SQLException {
        return User.getInstance(result);
    }

    @Override
    public User create(CreateUserDto userDto) {
        String query = """
                INSERT INTO users (name, email, age, roli, password_hash, salt)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, userDto.getName());
            pstm.setString(2, userDto.getEmail());
            pstm.setInt(3, userDto.getAge());
            pstm.setString(4, userDto.getRoli());
            pstm.setString(5, userDto.getPasswordHash());
            pstm.setString(6, userDto.getSalt());
            pstm.execute();

            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(UpdateUserDto userDto) {
        String query = """
                UPDATE users 
                SET email = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, userDto.getEmail());
            pstm.setInt(2, userDto.getId());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(userDto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                return fromResultSet(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countAll() {
        String query = "SELECT COUNT(*) FROM users";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int countByRoli(String roli) {
        String query = "SELECT COUNT(*) FROM users WHERE roli = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, roli);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
