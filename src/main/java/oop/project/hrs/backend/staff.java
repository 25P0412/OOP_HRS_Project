package oop.project.hrs.backend;
import java.time.LocalDate;

public abstract class staff {
    private Role role;
    private String username;
    private String password; // FIX: Changed data type to string, changed var name to password for better integration
    private LocalDate dateofbirth;
    private int workinghours;

    public staff(Role role, String username, String password, LocalDate dateofbirth, int workinghours) {
        this.role = role;
        this.username = username;
        this.pass = pass;
        this.dateofbirth = dateofbirth;
        this.workinghours = workinghours;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public int getPass() {
        return pass;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public int getWorkinghours() {
        return workinghours;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public void setWorkinghours(int workinghours) {
        if (workinghours < 0) {
            throw new IllegalArgumentException("Working hours cannot be negative!");
        }
        this.workinghours = workinghours;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
}
