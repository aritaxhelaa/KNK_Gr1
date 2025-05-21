package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rruga {
    private int id;
    private String emri;
    private int komunaId;
    private int qytetiId;
    private int fshatiId;
    private int lagjjaId;

    private Rruga(int id, String emri, int komunaId, int qytetiId,int fshatiId, int lagjjaId) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
        this.qytetiId = qytetiId;
        this.fshatiId = fshatiId;
        this.lagjjaId = lagjjaId;
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

    public int getQytetiId() {
        return qytetiId;
    }

    public void setQytetiId(int qytetiId) {
        this.qytetiId = qytetiId;
    }

    public int getFshatiId() {
        return fshatiId;
    }

    public void setFshatiId(int fshatiId) {
        this.fshatiId = fshatiId;
    }

    public int getLagjjaId() {
        return lagjjaId;
    }

    public void setLagjjaId(int lagjjaId) {
        this.lagjjaId = lagjjaId;
    }



    public static Rruga getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emri = resultSet.getString("emri");
        int komunaId = resultSet.getInt("komuna_id");
        int qytetiId = resultSet.getInt("qyteti_id");
        int fshatiId = resultSet.getInt("fshati_id");
        int lagjjaId = resultSet.getInt("lagjja_id");

        return new Rruga(id, emri, komunaId, qytetiId, fshatiId, lagjjaId);
    }

    @Override
    public String toString() {
        return emri;
    }
}