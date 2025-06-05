package com.praktikum.users;

import com.praktikum.actions.AdminActions;

public class Admin extends User implements AdminActions {

    public Admin(String username, String password) {
        // Nama & NIM bisa dummy karena tidak terlalu dipakai
        super(username, password, "admin", "381");
    }

    @Override
    public boolean login() {
        System.out.println("Login Admin berhasil!\n");
        return true;
    }

    @Override
    public void displayInfo() {
        System.out.println("==================================");
        System.out.println("         HALAMAN ADMIN");
        System.out.println("==================================");
        System.out.printf("Username Admin : %s\n", getUsername());
        System.out.println("==================================\n");
    }

    @Override
    public void manageItems() {
        // Kosongkan atau ganti dengan versi GUI kalau sudah pindah ke JavaFX
    }

    @Override
    public void manageUsers() {
        // Kosongkan atau ganti dengan versi GUI kalau sudah pindah ke JavaFX
    }

    @Override
    public void displayAppMenu() {
        // Tidak perlu dipakai di versi JavaFX
    }
}
