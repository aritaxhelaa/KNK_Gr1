package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String email;
    private int age;
    private String roli;
    private String passwordHash;
    private String salt;

    private User(int id, String name, String email, int age, String roli, String passwordHash, String salt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.roli = roli;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public static User getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        String email = result.getString("email");
        int age = result.getInt("age");
        String roli = result.getString("roli");
        String passwordHash = result.getString("password_hash");
        String salt = result.getString("salt");
        return new User(id, name, email, age, roli, passwordHash, salt);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getRoli() { return roli; }
    public String getPasswordHash() { return passwordHash; }
    public String getSalt() { return salt; }

    @Override
    public String toString() {
        return name + " (" + roli + ")";
    }
}
