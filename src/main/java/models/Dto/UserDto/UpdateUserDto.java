package models.Dto.UserDto;

public class UpdateUserDto {
    private int id;
    private String email;
    private String roli;

    public UpdateUserDto() {}

    public UpdateUserDto(int id, String email, String roli) {
        this.id = id;
        this.email = email;
        this.roli = roli;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRoli() {
        return roli;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }
}
