package models;

public class Objekti {
    private int id;
    private String lloji;
    private String emri;
    private int rrugaId;
    private String numriNderteses;
    private String koordinatatGPS;
    private int kodiPostarId;

    private Objekti(int id, String lloji, String emri, int rrugaId, String numriNderteses,
                    String koordinatatGPS, int kodiPostarId) {
        this.id = id;
        this.lloji = lloji;
        this.emri = emri;
        this.rrugaId = rrugaId;
        this.numriNderteses = numriNderteses;
        this.koordinatatGPS = koordinatatGPS;
        this.kodiPostarId = kodiPostarId;
    }
}
