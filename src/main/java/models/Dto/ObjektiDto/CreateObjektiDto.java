package models.Dto.ObjektiDto;

public class CreateObjektiDto {
    private int tipiObjektitId;
    private String emri;
    private int rrugaId;
    private String numriNderteses;
    private int kodiPostarId;

    public CreateObjektiDto(int tipiObjektitId, String emri, int rrugaId, String numriNderteses, int kodiPostarId) {
        this.tipiObjektitId = tipiObjektitId;
        this.emri = emri;
        this.rrugaId = rrugaId;
        this.numriNderteses = numriNderteses;
        this.kodiPostarId = kodiPostarId;
    }

    public int getTipiObjektitId() {
        return tipiObjektitId;
    }

    public void setTipiObjektitId(int tipiObjektitId) {
        this.tipiObjektitId = tipiObjektitId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public int getRrugaId() {
        return rrugaId;
    }

    public void setRrugaId(int rrugaId) {
        this.rrugaId = rrugaId;
    }

    public String getNumriNderteses() {
        return numriNderteses;
    }

    public void setNumriNderteses(String numriNderteses) {
        this.numriNderteses = numriNderteses;
    }

    public int getKodiPostarId() {
        return kodiPostarId;
    }

    public void setKodiPostarId(int kodiPostarId) {
        this.kodiPostarId = kodiPostarId;
    }
}