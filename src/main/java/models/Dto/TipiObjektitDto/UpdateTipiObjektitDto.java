package models.Dto.TipiObjektitDto;

public class UpdateTipiObjektitDto {
    private int id;
    private String emri;

    public UpdateTipiObjektitDto(int id, String emri) {
        this.id = id;
        this.emri = emri;
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
}
