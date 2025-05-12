package models.Dto.KomunaDto;

public class UpdateKomunaDto {
    private int id;
    private String emri;

    public UpdateKomunaDto(int id, String emri) {
        this.id = id;
        this.emri = emri;
    }

    public int getId() { return id; }
    public String getEmri() { return emri; }

    public void setEmri(String emri) { this.emri = emri; }
}
