package models.Dto.LagjjaDto;

public class UpdateLagjjaDto {
    private int id;
    private String emri;
    private int qytetiId;
    private int komunaId;

    public UpdateLagjjaDto(int id, String emri, int qytetiId, int komunaId) {
        this.id = id;
        this.emri = emri;
        this.qytetiId = qytetiId;
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