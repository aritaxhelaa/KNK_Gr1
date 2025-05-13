package models.Dto.KomunaDto;

public class UpdateKomunaDto {
    private int id;
    private String emri;


    public UpdateKomunaDto(int id, String emri) {
        this.id = id;
        this.emri = emri;
//        this.kodi_postar = kodi_postar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

//    public Integer getKodi_postar() {
//        return kodi_postar;
//    }

//    public void setKodi_postar(Integer kodi_postar) {
//        this.kodi_postar = kodi_postar;
//    }
}
