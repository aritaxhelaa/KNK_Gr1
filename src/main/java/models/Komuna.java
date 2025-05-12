package models;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Komuna {
   private int id;
    private String emri;
    public Komuna(int id, String emri) {
        this.id = id;
       this.emri = emri;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmri() { return emri; }
    public void setEmri(String emri) { this.emri = emri; }

    public static Komuna getInstance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String emri = rs.getString("emri");
        return new Komuna(id, emri);
    }

    @Override
    public String toString() {
        return emri;
    }
}
