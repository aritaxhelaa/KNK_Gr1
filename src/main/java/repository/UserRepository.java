package repository;

import Database.DBConnector;
import models.User;
import models.Dto.UserDto.CreateUserDto;
import models.Dto.UserDto.UpdateUserDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
            SET email = ?, roli = ?
            WHERE id = ?
            """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, userDto.getEmail());
            pstm.setString(2, userDto.getRoli());
            pstm.setInt(3, userDto.getId());

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

    /**
     * Numëron përdoruesit aktivë (përdoruesit që kanë kryer login në 30 ditët e fundit)
     * Kjo metodë kthehet gjithmonë countAll() derisa të shtohet kolona last_login në databazë
     */
    public int countActiveUsers() {
        try {
            // Provon me e perdor last_login nese ekziston
            String query = "SELECT COUNT(*) FROM users WHERE last_login >= DATE_SUB(NOW(), INTERVAL 30 DAY)";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            // Nese ndodh ndonje gabim (psh kolona nuk ekziston), kthe numrin total
            return countAll();
        }
    }

    /**
     * Përditëson kohën e fundit të kyçjes për një përdorues
     * Thirreni këtë metodë pas çdo kyçjeje të suksesshme
     * Kjo metodë nuk do të bëjë asgjë nëse kolona last_login nuk ekziston
     */
    public void updateLastLogin(int userId) {
        try {
            String query = "UPDATE users SET last_login = NOW() WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            // Injoro gabimin nese kolona nuk ekziston
        }
    }


    public Map<String, Integer> getRegistrimetMujore() {
        Map<String, Integer> rezultatet = new HashMap<>();

        // Inicializimi i krejt mujve me 0
        for (int i = 1; i <= 12; i++) {
            rezultatet.put(String.valueOf(i), 0);
        }

        String query = """
            SELECT EXTRACT(MONTH FROM created_at) AS muaji, COUNT(*) AS numri_perdoruesve
            FROM users
            WHERE EXTRACT(YEAR FROM created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
            GROUP BY EXTRACT(MONTH FROM created_at)
            ORDER BY muaji
            """;

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int muaji = rs.getInt("muaji"); // numer 1–12
                int numri = rs.getInt("numri_perdoruesve");
                rezultatet.put(String.valueOf(muaji), numri);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultatet;
    }

}