package models.Dto.ObjektiDto;

public class UpdateObjektiDto {
    private int id;
    private int tipiObjektitId;
    private String emri;
    private int rrugaId;
    private String numriNderteses;
    private int kodiPostarId;

    public UpdateObjektiDto(int id, int tipiObjektitId, String emri, int rrugaId, String numriNderteses, int kodiPostarId) {
        this.id = id;
        this.tipiObjektitId = tipiObjektitId;
        this.emri = emri;
        this.rrugaId = rrugaId;
        this.numriNderteses = numriNderteses;
        this.kodiPostarId = kodiPostarId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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