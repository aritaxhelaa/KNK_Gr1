package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KodiPostar {
    private int id;
    private String kodi;
    private String nenregjioni;
    private String regjioni;
    private int komunaId;

    public KodiPostar(int id, String kodi, String nenregjioni, String regjioni, int komunaId) {
        this.id = id;
        this.kodi = kodi;
        this.nenregjioni = nenregjioni;
        this.regjioni = regjioni;
        this.komunaId = komunaId;
    }

    public int getId() { return id; }
    public String getKodi() { return kodi; }
    public String getNenregjioni() { return nenregjioni; }
    public String getRegjioni() { return regjioni; }
    public int getKomunaId() { return komunaId; }

    public void setId(int id) { this.id = id; }
    public void setKodi(String kodi) { this.kodi = kodi; }
    public void setNenregjioni(String nenregjioni) { this.nenregjioni = nenregjioni; }
    public void setRegjioni(String regjioni) { this.regjioni = regjioni; }
    public void setKomunaId(int komunaId) { this.komunaId = komunaId; }

    public static KodiPostar getInstance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String kodi = rs.getString("kodi");
        String nenregjioni = rs.getString("nenregjioni");
        String regjioni = rs.getString("regjioni");
        int komunaId = rs.getInt("komuna_id");

        return new KodiPostar(id, kodi, nenregjioni, regjioni, komunaId);
    }

    @Override
    public String toString() {
        return kodi + " - " + nenregjioni + " (" + regjioni + ")";
    }
}
