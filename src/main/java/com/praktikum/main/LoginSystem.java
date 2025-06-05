package com.praktikum.main;

import com.praktikum.data.DataStore;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

public class LoginSystem {

    // Method login yang dipanggil dari GUI
    public static User login(String username, String password, String role) {
        System.out.println("DEBUG: Mencoba login sebagai " + username + " dengan role " + role);

        for (User user : DataStore.getUsers()) {
            System.out.println("DEBUG: Mengecek user " + user.getUsername());

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (role.equalsIgnoreCase("Admin") && user instanceof Admin) {
                    return user;
                } else if (role.equalsIgnoreCase("Mahasiswa") && user instanceof Mahasiswa) {
                    return user;
                }
            }
        }

        return null; // login gagal
    }
}
