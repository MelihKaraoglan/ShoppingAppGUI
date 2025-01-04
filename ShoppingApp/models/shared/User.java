package models.shared;



public class User {
    private String name;   // Kullanıcının ismi
    private String email;  // Kullanıcı adı ya da e-posta
    private String password;
    private Role role;

    // Enum olan Role için örnek
    public enum Role {
        ADMIN,USER
    }

    // Yapıcı metod
    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter ve Setter metotları
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}







