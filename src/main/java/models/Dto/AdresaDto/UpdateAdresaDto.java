package models.Dto.AdresaDto;

public class UpdateAdresaDto {
    private int id;
    private String rruga;
    private Integer numri;
    private Integer kodi_postar;

    public UpdateAdresaDto(int id, String rruga, Integer numri, Integer kodi_postar) {
        this.id = id;
        this.rruga = rruga;
        this.numri = numri;
        this.kodi_postar = kodi_postar;
    }

    public int getId() {
        return id;
    }

    public String getRruga() {
        return rruga;
    }

    public Integer getNumri() {
        return numri;
    }

    public Integer getKodi_postar() {
        return kodi_postar;
    }

    public void setRruga(String rruga) {
        this.rruga = rruga;
    }

    public void setNumri(int numri) {
        this.numri = numri;
    }

    public void setKodi_postar(int kodi_postar) {
        this.kodi_postar = kodi_postar;
    }
}