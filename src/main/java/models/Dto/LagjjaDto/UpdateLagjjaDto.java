package models.Dto.LagjjaDto;

public class UpdateLagjjaDto {
    private int id;
    private String emri;
    private int komunaId;
    private Double siperfaqja;
    private String pershkrimi;
    private Boolean statusiZyrtar;

    public UpdateLagjjaDto(int id, String emri, int komunaId, Double siperfaqja, String pershkrimi, Boolean statusiZyrtar) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
        this.siperfaqja = siperfaqja;
        this.pershkrimi = pershkrimi;
        this.statusiZyrtar = statusiZyrtar;
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

    public Double getSiperfaqja() {
        return siperfaqja;
    }

    public void setSiperfaqja(Double siperfaqja) {
        this.siperfaqja = siperfaqja;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public Boolean isStatusiZyrtar() {
        return statusiZyrtar;
    }

    public void setStatusiZyrtar(Boolean statusiZyrtar) {
        this.statusiZyrtar = statusiZyrtar;
    }
}
