package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Objekti {
    private int id;
    private TipiObjektit tipiObjektit;
    private String emri;
    private int rrugaId;
    private String numriNderteses;
    private String koordinatatGPS;
    private int kodiPostarId;

    private Objekti(int id, TipiObjektit tipiObjektit, String emri, int rrugaId, String numriNderteses,
                    String koordinatatGPS, int kodiPostarId) {
        this.id = id;
        this.tipiObjektit = tipiObjektit;
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

    public TipiObjektit getTipiObjektit() {
        return tipiObjektit;
    }

    public void setTipiObjektit(TipiObjektit tipiObjektit) {
        this.tipiObjektit = tipiObjektit;
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
        int tipiObjektitId = resultSet.getInt("tipi_objektit_id");
        String emri = resultSet.getString("emri");
        int rrugaId = resultSet.getInt("rruga_id");
        String numriNderteses = resultSet.getString("numri_nderteses");
        String koordinatatGPS = resultSet.getString("koordinatat_gps");
        int kodiPostarId = resultSet.getInt("kodi_postar_id");

        String tipiEmri = resultSet.getString("tipi_objektit_emri"); // duhet ta lexosh këtë nga databaza
        TipiObjektit tipiObjektit = new TipiObjektit(tipiObjektitId, tipiEmri);

        return new Objekti(id, tipiObjektit, emri, rrugaId, numriNderteses, koordinatatGPS, kodiPostarId);
    }

    @Override
    public String toString() {
        return emri + " (" + tipiObjektit.getEmri() + ")";
    }
}