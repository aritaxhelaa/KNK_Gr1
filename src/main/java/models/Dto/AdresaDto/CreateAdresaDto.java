package models.Dto.AdresaDto;

public class CreateAdresaDto {
    private int id;
    private String rruga;
    private int numri;
    private int kodi_postar;

    public CreateAdresaDto(int id, String rruga, int numri, int kodi_postar) {
        this.id = id;
        this.rruga = rruga;
        this.numri = numri;
        this.kodi_postar = kodi_postar;
    }

    public int getId(){
        return id;
    }

    public String getRruga() {
        return rruga;
    }

    public void setRruga(String rruga) {
        this.rruga = rruga;
    }

    public int getNumri() {
        return numri;
    }

    public void setNumri(int numri) {
        this.numri = numri;
    }

    public int getKodi_postar() {
        return kodi_postar;
    }

    public void setKodi_postar(int kodi_postar) {
        this.kodi_postar = kodi_postar;
    }
}
