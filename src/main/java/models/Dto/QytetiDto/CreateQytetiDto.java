package models.Dto.QytetiDto;

public class CreateQytetiDto {
    private String emri;
    private int komunaId;

    public CreateQytetiDto(String emri, int komunaId) {
        this.emri = emri;
        this.komunaId = komunaId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public int getKomunaId() {
        return komunaId;
    }

    public void setKomunaId(int komunaId) {
        this.komunaId = komunaId;
    }
}
