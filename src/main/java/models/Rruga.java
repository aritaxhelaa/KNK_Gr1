package models;

public class Rruga {
    private int id;
    private String emri;
    private int komunaId;
    private int qytetiId;
    private String kategoria;
    private double gjatesiaKM;

    private Rruga(int id, String emri, int komunaId, int qytetiId, String kategoria, double gjatesiaKM) {
        this.id = id;
        this.emri = emri;
        this.komunaId = komunaId;
        this.qytetiId = qytetiId;
        this.kategoria = kategoria;
        this.gjatesiaKM = gjatesiaKM;
    }
}
