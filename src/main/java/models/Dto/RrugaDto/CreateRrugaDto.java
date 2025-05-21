package models.Dto.RrugaDto;

public class CreateRrugaDto {
    private String emri;
    private int komunaId;
    private Integer qytetiId;  // Nullable
    private Integer fshatiId;  // Nullable
    private Integer lagjjaId;  // Opsionale, nëse don me pas

    public CreateRrugaDto(String emri, int komunaId, Integer qytetiId, Integer fshatiId, Integer lagjjaId) {
        this.emri = emri;
        this.komunaId = komunaId;
        this.qytetiId = qytetiId;
        this.fshatiId = fshatiId;
        this.lagjjaId = lagjjaId;
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

    public Integer getQytetiId() {
        return qytetiId;
    }

    public void setQytetiId(Integer qytetiId) {
        this.qytetiId = qytetiId;
    }

    public Integer getFshatiId() {
        return fshatiId;
    }

    public void setFshatiId(Integer fshatiId) {
        this.fshatiId = fshatiId;
    }

    public Integer getLagjjaId() {
        return lagjjaId;
    }

    public void setLagjjaId(Integer lagjjaId) {
        this.lagjjaId = lagjjaId;
    }
}
