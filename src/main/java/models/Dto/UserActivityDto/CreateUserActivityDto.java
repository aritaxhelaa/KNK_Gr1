package models.Dto.UserActivityDto;

public class CreateUserActivityDto {
    private int userId;
    private String veprimi;
    private String entiteti;
    private int entitetiId;

    public CreateUserActivityDto(int userId, String veprimi, String entiteti, int entitetiId) {
        this.userId = userId;
        this.veprimi = veprimi;
        this.entiteti = entiteti;
        this.entitetiId = entitetiId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVeprimi() {
        return veprimi;
    }

    public void setVeprimi(String veprimi) {
        this.veprimi = veprimi;
    }

    public String getEntiteti() {
        return entiteti;
    }

    public void setEntiteti(String entiteti) {
        this.entiteti = entiteti;
    }

    public int getEntitetiId() {
        return entitetiId;
    }

    public void setEntitetiId(int entitetiId) {
        this.entitetiId = entitetiId;
    }
}
