package models.Dto.TipiObjektitDto;

public class CreateTipiObjektitDto {
    private String emri;

    public CreateTipiObjektitDto(String emri) {
        this.emri = emri;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }
}
