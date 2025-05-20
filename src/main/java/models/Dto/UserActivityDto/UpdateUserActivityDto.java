package models.Dto.UserActivityDto;

public class UpdateUserActivityDto {
    private int id;
    private String data;
    private String adresa;

    public UpdateUserActivityDto() {
    }

    public UpdateUserActivityDto( int id, String data, String adresa) {
        this.id = id;
        this.data = data;
        this.adresa = adresa;
    }

    public int getId() {
       return this.id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
