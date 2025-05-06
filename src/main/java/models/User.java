package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String email;
    private int age;
    private String roli; // "admin", "komunal", "qytetar"

    public User(int id, String name, String email, int age, String roli) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.roli = roli;
    }

    public static User getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        String email = result.getString("email");
        int age = result.getInt("age");
        String roli = result.getString("roli");
        return new User(id, name, email, age, roli);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getRoli() { return roli; }

//    public void setId(int id) { this.id = id; }
//    public void setName(String name) { this.name = name; }
//    public void setEmail(String email) { this.email = email; }
//    public void setAge(int age) { this.age = age; }
//    public void setRoli(String roli) { this.roli = roli; }

    @Override
    public String toString() {
        return name + " (" + roli + ")";
    }
}
