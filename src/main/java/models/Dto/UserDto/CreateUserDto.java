package models.Dto.UserDto;

public class CreateUserDto {
    private String name;
    private String email;
    private int age;
    private String roli;
    private String passwordHash;
    private String salt;

    public CreateUserDto(String name, String email, int age, String roli, String passwordHash, String salt) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.roli = roli;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public CreateUserDto(String name, String email, int age, String roli) {
        this(name, email, age, roli, null, null);
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getRoli() { return roli; }
    public String getPasswordHash() { return passwordHash; }
    public String getSalt() { return salt; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setRoli(String roli) { this.roli = roli; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setSalt(String salt) { this.salt = salt; }
}
