package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Objekti {
    private int id;
    private String lloji;
    private String emri;
    private int rrugaId;
    private String numriNderteses;
    private String koordinatatGPS;
    private int kodiPostarId;

    private Objekti(int id, String lloji, String emri, int rrugaId, String numriNderteses,
                    String koordinatatGPS, int kodiPostarId) {
        this.id = id;
        this.lloji = lloji;
        this.emri = emri;
        this.rrugaId = rrugaId;
        this.numriNderteses = numriNderteses;
        this.koordinatatGPS = koordinatatGPS;
        this.kodiPostarId = kodiPostarId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLloji() {
        return lloji;
    }

    public void setLloji(String lloji) {
        this.lloji = lloji;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public int getRrugaId() {
        return rrugaId;
    }

    public void setRrugaId(int rrugaId) {
        this.rrugaId = rrugaId;
    }

    public String getNumriNderteses() {
        return numriNderteses;
    }

    public void setNumriNderteses(String numriNderteses) {
        this.numriNderteses = numriNderteses;
    }

    public String getKoordinatatGPS() {
        return koordinatatGPS;
    }

    public void setKoordinatatGPS(String koordinatatGPS) {
        this.koordinatatGPS = koordinatatGPS;
    }

    public int getKodiPostarId() {
        return kodiPostarId;
    }

    public void setKodiPostarId(int kodiPostarId) {
        this.kodiPostarId = kodiPostarId;
    }

    public static Objekti getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String lloji = resultSet.getString("lloji");
        String emri = resultSet.getString("emri");
        int rrugaId = resultSet.getInt("rruga_id");
        String numriNderteses = resultSet.getString("numri_nderteses");
        String koordinatatGPS = resultSet.getString("koordinatat_gps");
        int kodiPostarId = resultSet.getInt("kodi_postar_id");

        return new Objekti(id, lloji, emri, rrugaId, numriNderteses, koordinatatGPS, kodiPostarId);
    }

    @Override
    public String toString() {
        return emri + " (" + lloji + ")";
    }
}