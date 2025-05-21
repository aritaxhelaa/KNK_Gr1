package models.Dto.RrugaDto;

public class UpdateRrugaDto {
    private int id;
    private String emri;
    private int komunaId;
    private int qytetiId;
    private int fshatiId;
    private int lagjjaId;

    public UpdateRrugaDto(int id, String emri, int komunaId, int qytetiId, int fshatiId, int lagjjaId) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
        this.qytetiId = qytetiId;
        this.fshatiId = fshatiId;
        this.lagjjaId = lagjjaId;
    }

    public int getId() { return id; }
    public String getEmri() { return emri; }
    public int getKomunaId() { return komunaId; }
    public int getQytetiId() { return qytetiId; }
    public int getFshatiId() { return fshatiId; }
    public int getLagjjaId() { return lagjjaId; }
}
