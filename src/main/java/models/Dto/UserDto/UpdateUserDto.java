package models.Dto.UserDto;

public class UpdateUserDto {
    private int id;
    private String email;
    private String roli; //u shtu

    public UpdateUserDto() {}

    public UpdateUserDto(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRoli() {
        return roli; // ✅ SHTO KËTË
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoli(String roli) { // edhe kjo
        this.roli = roli;
    }
}
