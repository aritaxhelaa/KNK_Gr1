package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Fshati {
    private int id;
    private String emri;
    private int komunaId;

    private Fshati(int id, String emri, int komunaId) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public int getKomunaId() {
        return komunaId;
    }

    public void setKomunaId(int komunaId) {
        this.komunaId = komunaId;
    }

    public static Fshati getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emri = resultSet.getString("emri");
        int komunaId = resultSet.getInt("komuna_id");

        return new Fshati(id, emri, komunaId);
    }

    @Override
    public String toString() {
        return emri;
    }
}
