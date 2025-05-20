package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserActivity {
    private final int userId;
    private final String data;
    private final String adresa;

    public UserActivity(int userId, String data, String adresa) {
        this.userId = userId;
        this.data = data;
        this.adresa = adresa;
    }

    public int getUserId() {
        return userId;
    }

    public String getData() {
        return data;
    }

    public String getAdresa() {
        return adresa;
    }

    public static UserActivity getInstance(ResultSet rs) throws SQLException {
        return new UserActivity(
                rs.getInt("id"),
                rs.getString("data"),
                rs.getString("adresa")
        );
    }
}