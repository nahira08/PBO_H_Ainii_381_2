package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
import com.praktikum.users.Mahasiswa;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MahasiswaDashboard {

    private VBox root;
    private TableView<Item> table;
    private ObservableList<Item> mahasiswaItems;
    private Mahasiswa user;
    private Stage stage;

    public MahasiswaDashboard(Stage stage, Mahasiswa user) {
        this.stage = stage;
        this.user = user;

        // Set ukuran jendela otomatis besar
        stage.setWidth(750);
        stage.setHeight(500);

        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #ffeaf4;");

        Label welcomeLabel = new Label("Selamat datang, " + user.getNama() + " ♡");
        Label formLabel = new Label("Laporkan Barang Hilang/Temuan");
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,20));

        // Input Field
        TextField namaField = new TextField();
        namaField.setPromptText("Nama Barang");

        TextField deskripsiField = new TextField();
        deskripsiField.setPromptText("Deskripsi Barang");

        TextField lokasiField = new TextField();
        lokasiField.setPromptText("Lokasi Kehilangan");

        // Tombol Laporkan
        Button laporButton = new Button("Laporkan");
        laporButton.setOnAction(e -> {
            String nama = namaField.getText().trim();
            String deskripsi = deskripsiField.getText().trim();
            String lokasi = lokasiField.getText().trim();

            if (!nama.isEmpty() && !lokasi.isEmpty()) {
                Item item = new Item(nama, deskripsi, lokasi, user.getUsername(), false);
                DataStore.addItem(item);
                mahasiswaItems.add(item);

                namaField.clear();
                deskripsiField.clear();
                lokasiField.clear();
            }
        });

        HBox inputBox = new HBox(5, namaField, deskripsiField, lokasiField, laporButton);

        // Tabel Laporan Mahasiswa
        table = new TableView<>();
        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> {
            boolean claimed = cellData.getValue().isClaimed();
            return new ReadOnlyStringWrapper(claimed ? "Claimed" : "Reported");
        });

        table.getColumns().addAll(namaCol, lokasiCol, statusCol);

        mahasiswaItems = FXCollections.observableArrayList();
        for (Item item : DataStore.getReportedItems()) {
            if (item.getPelapor().equals(user.getUsername())) {
                mahasiswaItems.add(item);
            }
        }

        table.setItems(mahasiswaItems);

        Label daftarLabel = new Label("Daftar Laporan Anda");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            stage.getScene().setRoot(new LoginPane(stage).getPane());
        });

        root.getChildren().addAll(welcomeLabel, formLabel, inputBox, daftarLabel, table, logoutButton);
    }

    public VBox getPane() {
        return root;
    }
}
