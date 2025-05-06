package models.Dto.UserDto;

public class CreateUserDto {
    private String name;
    private String email;
    private int age;
    private String roli;

    public CreateUserDto(String name, String email, int age, String roli) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.roli = roli;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getRoli() { return roli; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setRoli(String roli) { this.roli = roli; }
}
