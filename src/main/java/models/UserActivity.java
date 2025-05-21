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

    public String getData() {
        return data;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getUserId() {
        return userId;
    }


    public static UserActivity getInstance(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("search_time");
        return new UserActivity(
                rs.getInt("user_id"),
                ts != null ? ts.toLocalDateTime().toLocalDate().toString() : "",
                rs.getString("adresa")
        );
    }
}
