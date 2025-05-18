package utils;

public class SessionSearchData {
    private String komuna;
    private String lloji;
    private String vendbanimi;
    private String adresa;

    public SessionSearchData(String komuna, String lloji, String vendbanimi, String adresa) {
        this.komuna = komuna;
        this.lloji = lloji;
        this.vendbanimi = vendbanimi;
        this.adresa = adresa;
    }

    public String getKomuna() {
        return komuna;
    }

    public String getLloji() {
        return lloji;
    }

    public String getVendbanimi() {
        return vendbanimi;
    }

    public String getAdresa() {
        return adresa;
    }
}
