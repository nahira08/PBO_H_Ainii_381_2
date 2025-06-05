package com.praktikum.data;

import java.util.ArrayList;
import java.util.List;

import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

public class DataStore {

    // Menyimpan data semua item yang dilaporkannyaa (lost & found)
    private static List<Item> items = new ArrayList<>();

    // Menyimpan data semua penggunanyaa (Admin & Mahasiswa)
    private static List<User> users = new ArrayList<>();

    // Static block akan dieksekusi satu kali saat class pertama kali digunakan
    static {
        // Menambahkan 1 akun Admin dummy (username: admin, password: admin)
        users.add(new Admin("admin", "admin"));

        // Menambahkan 2 akun Mahasiswa dummy
        users.add(new Mahasiswa("aini", "381", "Nur Aini", "202410370110381"));
        users.add(new Mahasiswa("radya", "321", "Muhammad Radya", "202410370110370"));

        // Menambahkan 2 barang dummy yang dilaporkan mahasiswa
        // Format: (nama barang, deskripsi, lokasi, pelapor, status claimed)
        items.add(new Item("Laptop Charger", "Cas Laptop", "Lab A", "aini", false));
        items.add(new Item("Mouse", "Alat Elektronik", "Lab C", "radya", false));
    }

    // Mengambil semua data barang yang telah dilaporkan
    public static List<Item> getReportedItems() {
        return items;
    }

    // Menambahkan data barang baru ke dalam sistem
    public static void addItem(Item item) {
        items.add(item);
    }

    // Mengambil semua user (Admin dan Mahasiswa)
    public static List<User> getUsers() {
        return users;
    }

    // Menambahkan user baru ke dalam sistem
    public static void addUser(User user) {
        users.add(user);
    }
}
