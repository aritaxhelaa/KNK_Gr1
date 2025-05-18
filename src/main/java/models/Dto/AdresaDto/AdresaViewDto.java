package models.Dto.AdresaDto;


public class AdresaViewDto {
    private String komuna;
    private String vendbanimi;
    private String lagjia;
    private String adresa;
    private int kodiPostar;

    public AdresaViewDto(String komuna, String vendbanimi, String lagjia, String adresa, int kodiPostar) {
        this.komuna = komuna;
        this.vendbanimi = vendbanimi;
        this.lagjia = lagjia;
        this.adresa = adresa;
        this.kodiPostar = kodiPostar;
    }

    public String getKomuna() {
        return komuna;
    }

    public String getVendbanimi() {
        return vendbanimi;
    }

    public String getLagjia(){
        return lagjia;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getKodiPostar() {
        return kodiPostar;
    }

}
