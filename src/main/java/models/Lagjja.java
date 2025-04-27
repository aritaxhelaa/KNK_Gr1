package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lagjja {
    private int id;
    private String emri;
    private int qytetiId;
    private int komunaId;
    private double siperfaqja;
    private String pershkrimi;
    private boolean statusiZyrtar;

    private Lagjja(int id, String emri, int qytetiId, int komunaId, double siperfaqja, String pershkrimi, boolean statusiZyrtar) {
        this.id = id;
        this.emri = emri;
        this.qytetiId = qytetiId;
        this.komunaId = komunaId;
        this.siperfaqja = siperfaqja;
        this.pershkrimi = pershkrimi;
        this.statusiZyrtar = statusiZyrtar;
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

    public int getQytetiId() {
        return qytetiId;
    }

    public void setQytetiId(int qytetiId) {
        this.qytetiId = qytetiId;
    }

    public int getKomunaId() {
        return komunaId;
    }

    public void setKomunaId(int komunaId) {
        this.komunaId = komunaId;
    }

    public double getSiperfaqja() {
        return siperfaqja;
    }

    public void setSiperfaqja(double siperfaqja) {
        this.siperfaqja = siperfaqja;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public boolean isStatusiZyrtar() {
        return statusiZyrtar;
    }

    public void setStatusiZyrtar(boolean statusiZyrtar) {
        this.statusiZyrtar = statusiZyrtar;
    }

    public static Lagjja getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emri = resultSet.getString("emri");
        int qytetiId = resultSet.getInt("qyteti_id");
        int komunaId = resultSet.getInt("komuna_id");
        double siperfaqja = resultSet.getDouble("siperfaqja");
        String pershkrimi = resultSet.getString("pershkrimi");
        boolean statusiZyrtar = resultSet.getBoolean("statusi_zyrtar");

        return new Lagjja(id, emri, qytetiId, komunaId, siperfaqja, pershkrimi, statusiZyrtar);
    }

    @Override
    public String toString() {
        return emri;
    }
}