package models.Dto.UserActivityDto;

public class CreateUserActivityDto {
    private int userId;
    private String data;
    private String adresa;

    public CreateUserActivityDto() {
    }

    public CreateUserActivityDto(int userId, String data, String adresa) {
        this.userId = userId;
        this.data = data;
        this.adresa = adresa;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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