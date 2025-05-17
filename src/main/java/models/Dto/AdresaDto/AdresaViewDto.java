package models.Dto.AdresaDto;


public class AdresaViewDto {
    private String komuna;
    private String vendbanimi;
    private String lagjia;
    private String rruga;
    private int numri;
    private int kodiPostar;

    public AdresaViewDto(String komuna, String vendbanimi, String lagjia, String rruga, int numri, int kodiPostar) {
        this.komuna = komuna;
        this.vendbanimi = vendbanimi;
        this.lagjia = lagjia;
        this.rruga = rruga;
        this.numri = numri;
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

    public String getRruga() {
        return rruga;
    }

    public int getNumri() {
        return numri;
    }

    public int getKodiPostar() {
        return kodiPostar;
    }

    public String getAdresa() {
        return rruga + " " + numri;
    }

}
