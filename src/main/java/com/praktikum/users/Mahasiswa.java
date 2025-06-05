package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String username, String password, String nama, String nim) {
        super(username, password, nama, nim);
    }

    @Override
    public boolean login() {
        System.out.println("Login Mahasiswa sukses!\n");
        return true;
    }

    @Override
    public void displayInfo() {
        System.out.println("==================================");
        System.out.println("       HALAMAN MAHASISWA");
        System.out.println("==================================");
        System.out.printf("Nama Mahasiswa : %s\n", getNama());
        System.out.printf("NIM            : %s\n", getNim());
        System.out.printf("Username       : %s\n", getUsername());
        System.out.println("==================================\n");
    }

    @Override
    public void reportItem() {
        // Kosongkan / tidak dipakai di versi GUI
    }

    @Override
    public void viewReportedItems() {
        // Kosongkan / tidak dipakai di versi GUI
    }

    @Override
    public void displayAppMenu() {
        // Tidak dipakai di versi GUI
    }
}
