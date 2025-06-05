package com.praktikum.gui;

import com.praktikum.main.LoginSystem;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPane {

    private VBox root;
    private TextField usernameField;
    private PasswordField passwordField;
    private ComboBox<String> roleComboBox;
    private Label errorLabel;
    private Stage stage;

    public LoginPane(Stage stage) {
        this.stage = stage;
        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); // Tengah semua

        Label titleLabel = new Label("˚₊·Login Sistem Lost & Found₊˚⊹");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,20));

        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Mahasiswa", "Admin");
        roleComboBox.setValue("Mahasiswa");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

       TextField email = new TextField();
       email.setPromptText("Email"); 

        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(e -> handleLogin());

        errorLabel = new Label("Login gagal. Username atau password/NIM salah.");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false); // Default: tidak kelihatan

         Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            System.exit(0);
        });

        // Susun semua dalam VBox utama
        VBox top = new VBox(15,logoutButton);
        

        root.setStyle("-fx-background-color: #ffeaf4;");

        root.getChildren().addAll(titleLabel, roleComboBox, usernameField, passwordField, logoutButton, loginButton, errorLabel);
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleComboBox.getValue();

        User user = LoginSystem.login(username, password, role);

        if (user != null) {
            if (user instanceof Mahasiswa) {
                MahasiswaDashboard dashboard = new MahasiswaDashboard(stage, (Mahasiswa) user);
                stage.getScene().setRoot(dashboard.getPane());
            } else if (user instanceof Admin) {
                AdminDashboard dashboard = new AdminDashboard(stage, (Admin) user);
                stage.getScene().setRoot(dashboard.getPane());
            }
        } else {
            errorLabel.setVisible(true); // Munculkan error jika login gagal
        }
    }

    public VBox getPane() {
        return root;
    }
}
