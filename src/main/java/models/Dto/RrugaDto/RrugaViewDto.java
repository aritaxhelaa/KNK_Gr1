package models.Dto.RrugaDto;

public class RrugaViewDto {
    private String komuna;
    private String vendbanimi; // qyteti ose fshati
    private String lagjia;
    private String rruga;
    private String kodiPostar;

    public RrugaViewDto(String komuna, String vendbanimi, String lagjia,
                        String rruga, String kodiPostar) {
        this.komuna = komuna;
        this.vendbanimi = vendbanimi;
        this.lagjia = lagjia;
        this.rruga = rruga;
        this.kodiPostar = kodiPostar;
    }

    // Getters
    public String getKomuna() { return komuna; }
    public String getVendbanimi() { return vendbanimi; }
    public String getLagjia() { return lagjia; }
    public String getRruga() { return rruga; }
    public String getKodiPostar() { return kodiPostar; }
}