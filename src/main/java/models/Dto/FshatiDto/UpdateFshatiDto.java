package models.Dto.FshatiDto;

public class UpdateFshatiDto {
    private int id;
    private String emri;
    private int komunaId;

    public UpdateFshatiDto(int id, String emri, int komunaId) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
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

    public int getKomunaId() {
        return komunaId;
    }

    public void setKomunaId(int komunaId) {
        this.komunaId = komunaId;
    }
}
