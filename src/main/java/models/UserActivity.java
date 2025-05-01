package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserActivity {
    private int id;
    private int userId;
    private String veprimi;
    private String entiteti;
    private int entitetiId;
    private Timestamp data;

    private UserActivity(int id, int userId, String veprimi, String entiteti, int entitetiId, Timestamp data) {
        this.id = id;
        this.userId = userId;
        this.veprimi = veprimi;
        this.entiteti = entiteti;
        this.entitetiId = entitetiId;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getVeprimi() {
        return veprimi;
    }

    public String getEntiteti() {
        return entiteti;
    }

    public int getEntitetiId() {
        return entitetiId;
    }

    public Timestamp getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setVeprimi(String veprimi) {
        this.veprimi = veprimi;
    }

    public void setEntiteti(String entiteti) {
        this.entiteti = entiteti;
    }

    public void setEntitetiId(int entitetiId) {
        this.entitetiId = entitetiId;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public static UserActivity getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String veprimi = resultSet.getString("veprimi");
        String entiteti = resultSet.getString("entiteti");
        int entitetiId = resultSet.getInt("entiteti_id");
        Timestamp data = resultSet.getTimestamp("data");

        return new UserActivity(id, userId, veprimi, entiteti, entitetiId, data);
    }

    @Override
    public String toString() {
        return veprimi + " " + entiteti + " [" + entitetiId + "] në " + data;
    }
}
