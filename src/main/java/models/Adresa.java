package models;

import models.Dto.AdresaDto.AdresaViewDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Adresa {
    private int id;
    private String rruga;
    private int numri;
    private int kodiPostar;

    private Timestamp dataKerkimit; // ✅ për historikun e kërkimeve

    public Adresa() {
    }

    public Adresa(int id, String rruga, int numri, int kodiPostar) {
        this.id = id;
        this.rruga = rruga;
        this.numri = numri;
        this.kodiPostar = kodiPostar;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRruga() { return rruga; }
    public void setRruga(String rruga) { this.rruga = rruga; }

    public int getNumri() { return numri; }
    public void setNumri(int numri) { this.numri = numri; }

    public int getKodiPostar() { return kodiPostar; }
    public void setKodiPostar(int kodiPostar) { this.kodiPostar = kodiPostar; }

    public Timestamp getDataKerkimit() { return dataKerkimit; }
    public void setDataKerkimit(Timestamp dataKerkimit) { this.dataKerkimit = dataKerkimit; }

    public static Adresa getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String rruga = resultSet.getString("rruga");
        int numri = resultSet.getInt("numri");
        int kodiPostar = resultSet.getInt("kodi_postar");

        return new Adresa(id, rruga, numri, kodiPostar);
    }

    public static Adresa getInstanceFromViewDto(AdresaViewDto dto) {
        Adresa a = new Adresa();
        a.setRruga(dto.getRruga());
        a.setNumri(dto.getNumri());
        a.setKodiPostar(dto.getKodiPostar());
        return a;
    }
}
