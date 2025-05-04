package models.Dto.RrugaDto;

public class CreateRrugaDto {
    private String emri;
    private int komunaId;
    private int qytetiId;

    public CreateRrugaDto(String emri, int komunaId, int qytetiId) {
        this.emri = emri;
        this.komunaId = komunaId;
        this.qytetiId = qytetiId;
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

    public int getQytetiId() {
        return qytetiId;
    }

    public void setQytetiId(int qytetiId) {
        this.qytetiId = qytetiId;
    }
}