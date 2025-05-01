package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rruga {
    private int id;
    private String emri;
    private int komunaId;
    private int qytetiId;
    private String kategoria;
    private double gjatesiaKM;

    private Rruga(int id, String emri, int komunaId, int qytetiId, String kategoria, double gjatesiaKM) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
        this.qytetiId = qytetiId;
        this.kategoria = kategoria;
        this.gjatesiaKM = gjatesiaKM;
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

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public double getGjatesiaKM() {
        return gjatesiaKM;
    }

    public void setGjatesiaKM(double gjatesiaKM) {
        this.gjatesiaKM = gjatesiaKM;
    }

    public static Rruga getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emri = resultSet.getString("emri");
        int komunaId = resultSet.getInt("komuna_id");
        int qytetiId = resultSet.getInt("qyteti_id");
        String kategoria = resultSet.getString("kategoria");
        double gjatesiaKM = resultSet.getDouble("gjatesia_km");

        return new Rruga(id, emri, komunaId, qytetiId, kategoria, gjatesiaKM);
    }

    @Override
    public String toString() {
        return emri;
    }
}