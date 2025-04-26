package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lagjja {
    private int id;
    private String emri;
    private int qytetiId;
    private int komunaId;
    private double siperfaqja;
    private String pershkrimi;
    private boolean statusiZyrtar;

    private Lagjja(int id, String emri, int qytetiId, int komunaId, double siperfaqja, String pershkrimi, boolean statusiZyrtar) {
        this.id = id;
        this.emri = emri;
        this.qytetiId = qytetiId;
        this.komunaId = komunaId;
        this.siperfaqja = siperfaqja;
        this.pershkrimi = pershkrimi;
        this.statusiZyrtar = statusiZyrtar;
    }

}