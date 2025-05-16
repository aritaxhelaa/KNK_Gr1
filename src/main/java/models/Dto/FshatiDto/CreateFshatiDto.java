package models.Dto.FshatiDto;

public class CreateFshatiDto {
    private String emri;
    private int komunaId;

    public CreateFshatiDto(String emri, int komunaId) {
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
