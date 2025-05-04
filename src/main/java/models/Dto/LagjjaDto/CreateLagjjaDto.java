package models.Dto.LagjjaDto;

public class CreateLagjjaDto {
    private String emri;
    private int qytetiId;
    private int komunaId;

    public CreateLagjjaDto(String emri, int qytetiId, int komunaId) {
        this.emri = emri;
        this.qytetiId = qytetiId;
        this.komunaId = komunaId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public int getQytetiId() {
        return qytetiId;
    }

    public void setQytetiId(int qytetiId) {
        this.qytetiId = qytetiId;
    }

    public int getKomunaId() {
        return komunaId;
    }

    public void setKomunaId(int komunaId) {
        this.komunaId = komunaId;
    }
}