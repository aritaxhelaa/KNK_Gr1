package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipiObjektit {
    private int id;
    private String emri;

    private TipiObjektit(int id, String emri) {
        this.id = id;
        this.emri = emri;
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

    public static TipiObjektit getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emri = resultSet.getString("emri");

        return new TipiObjektit(id, emri);
    }

    @Override
    public String toString() {
        return emri;
    }
}
