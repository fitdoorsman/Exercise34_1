package com.example.exercise34_1;

public class Staff {
    private String id;
    private String lastName;
    private String firstName;
    private String mi;
    private String address;
    private String city;
    private String state;
    private String telephone;
    private String email;

    // Default constructor
    public Staff() {}

    // Parameterized constructor
    public Staff(String id, String lastName, String firstName, String mi,
                 String address, String city, String state, String telephone, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mi = mi;
        this.address = address;
        this.city = city;
        this.state = state;
        this.telephone = telephone;
        this.email = email;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMi() {
        return mi;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}