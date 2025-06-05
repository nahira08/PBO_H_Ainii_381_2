package com.praktikum.users;

public abstract class User {
    private String username;
    private String password;
    private String nama;
    private String nim;

    public User(String username, String password, String nama, String nim) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.nim = nim;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public abstract boolean login();
    public abstract void displayInfo();
    public abstract void displayAppMenu();
}

