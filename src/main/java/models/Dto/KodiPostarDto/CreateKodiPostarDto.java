package models.Dto.KodiPostarDto;

public class CreateKodiPostarDto {
    private String kodi;
    private String nenregjioni;
    private String regjioni;
    private int komunaId;

    public CreateKodiPostarDto(String kodi, String nenregjioni, String regjioni, int komunaId) {
        this.kodi = kodi;
        this.nenregjioni = nenregjioni;
        this.regjioni = regjioni;
        this.komunaId = komunaId;
    }

    public String getKodi() { return kodi; }
    public String getNenregjioni() { return nenregjioni; }
    public String getRegjioni() { return regjioni; }
    public int getKomunaId() { return komunaId; }

    public void setKodi(String kodi) { this.kodi = kodi; }
    public void setNenregjioni(String nenregjioni) { this.nenregjioni = nenregjioni; }
    public void setRegjioni(String regjioni) { this.regjioni = regjioni; }
    public void setKomunaId(int komunaId) { this.komunaId = komunaId; }
}
