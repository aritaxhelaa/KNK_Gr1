package models.Dto.UserActivityDto;

public class UpdateUserActivityDto {
    private int id;
    private String veprimi;
    private String entiteti;
    private int entitetiId;

    public UpdateUserActivityDto(int id, String veprimi, String entiteti, int entitetiId) {
        this.id = id;
        this.veprimi = veprimi;
        this.entiteti = entiteti;
        this.entitetiId = entitetiId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
