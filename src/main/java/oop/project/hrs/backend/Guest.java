package oop.project.hrs.backend;

import java.time.LocalDate;
//Public class Guest extends User
public class Guest {
    private String username;
    private String password; //Can't be empty, 8 characters at least
    private LocalDate dateOfBirth;
    private double balance;
    private String address;
    private Gender gender;
    //Waiting for roomPreferences it to be implemented
    public Guest(String username, String password, Gender gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
    }
    //Write login and register methods after db, write an exception too if username is taken.
    //Getter Setter methods
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
