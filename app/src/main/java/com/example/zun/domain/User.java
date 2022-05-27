package com.example.zun.domain;

import java.util.Objects;

public class User {
    private int id;

    private  String name;

    private  int gender_id;

    private String email;

    private String password;

    private int role;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && gender_id == user.gender_id && role == user.role && name.equals(user.name) && email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender_id, email, password, role);
    }

    @Override
    public String toString() {
        return id +" " + name;
    }

    public String getName() {
        return name;
    }

    public int getGender_id() {
        return gender_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public User(int id, String name, int gender_id, String email, String password, int role) {
        this.id = id;
        this.name = name;
        this.gender_id = gender_id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
