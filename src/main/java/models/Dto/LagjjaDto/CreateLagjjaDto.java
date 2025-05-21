package models.Dto.LagjjaDto;

public class CreateLagjjaDto {
    private String emri;
    private int komunaId;
    private double siperfaqja;
    private String pershkrimi;
    private boolean statusiZyrtar;

    public CreateLagjjaDto(String emri, int komunaId, double siperfaqja, String pershkrimi, boolean statusiZyrtar) {
        this.emri = emri;
        this.komunaId = komunaId;
        this.siperfaqja = siperfaqja;
        this.pershkrimi = pershkrimi;
        this.statusiZyrtar = statusiZyrtar;
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

    public double getSiperfaqja() {
        return siperfaqja;
    }

    public void setSiperfaqja(double siperfaqja) {
        this.siperfaqja = siperfaqja;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public boolean isStatusiZyrtar() {
        return statusiZyrtar;
    }

    public void setStatusiZyrtar(boolean statusiZyrtar) {
        this.statusiZyrtar = statusiZyrtar;
    }
}
