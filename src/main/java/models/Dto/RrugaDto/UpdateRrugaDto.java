package models.Dto.RrugaDto;

public class UpdateRrugaDto {
    private int id;
    private String emri;
    private int komunaId;
    private int qytetiId;

    public UpdateRrugaDto(int id, String emri, int komunaId, int qytetiId) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
        this.qytetiId = qytetiId;
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

    public int getQytetiId() {
        return qytetiId;
    }

    public void setQytetiId(int qytetiId) {
        this.qytetiId = qytetiId;
    }
}