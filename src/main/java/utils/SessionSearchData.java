package utils;

public class SessionSearchData {
    private String komuna;
    private String lloji;
    private String vendbanimi;
    private String rruga;

    public SessionSearchData(String komuna, String lloji, String vendbanimi, String rruga) {
        this.komuna = komuna;
        this.lloji = lloji;
        this.vendbanimi = vendbanimi;
        this.rruga = rruga;
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

    public String getRruga() {
        return rruga;
    }
}
