package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AdminDashboard {

    // === Komponen utama ===
    private BorderPane root;
    private TableView<Item> itemTable;
    private TableView<Mahasiswa> mahasiswaTable;
    private ObservableList<Item> allItems;
    private ObservableList<Mahasiswa> mahasiswaList;
    private Admin user;
    private Stage stage;

    // === Konstruktor saat admin login ===
    public AdminDashboard(Stage stage, Admin user) {
        this.stage = stage;
        this.user = user;

        // Root layout dan background aesthetic coquette
        root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #ffeaf4;");

        // Ukuran window saat admin login
        stage.setWidth(950);
        stage.setHeight(600);
        stage.setResizable(true);
        stage.centerOnScreen();

        // Label selamat datang dengan gaya aesthetic
        Label welcomeLabel = new Label("Halo, Administrator " + user.getUsername() + " ♡");
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

        // === TABEL LAPORAN BARANG ===
        itemTable = new TableView<>();

        // Kolom Nama Barang
        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        namaCol.setPrefWidth(120);

        // Kolom Lokasi
        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        lokasiCol.setPrefWidth(120);

        // Kolom Status (Claimed atau Reported)
        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> {
            boolean claimed = cellData.getValue().isClaimed();
            return new ReadOnlyStringWrapper(claimed ? "Claimed" : "Reported");
        });
        statusCol.setPrefWidth(100);

        // Tambahkan semua kolom ke tabel barang
        itemTable.getColumns().addAll(namaCol, lokasiCol, statusCol);

        // Ambil data barang dari DataStore
        allItems = FXCollections.observableArrayList(DataStore.getReportedItems());
        itemTable.setItems(allItems);

        // Tombol untuk menandai barang sebagai "Claimed"
        Button claimButton = new Button("Tandai Claimed ♡");
        claimButton.setOnAction(e -> {
            Item selected = itemTable.getSelectionModel().getSelectedItem();
            if (selected != null && !selected.isClaimed()) {
                selected.setClaimed(true);
                itemTable.refresh(); // update tampilan tabel
            }
        });

        // Kelompokkan ke dalam VBox laporan barang
        VBox laporanBox = new VBox(10, new Label("Laporan Barang"), itemTable, claimButton);
        laporanBox.setPrefWidth(350);

        // === TABEL MAHASISWA ===
        mahasiswaTable = new TableView<>();

        // Kolom Nama Mahasiswa
        TableColumn<Mahasiswa, String> namaMhsCol = new TableColumn<>("Nama");
        namaMhsCol.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getNama()));
        namaMhsCol.setPrefWidth(150);

        // Kolom NIM
        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(m -> new ReadOnlyStringWrapper(m.getValue().getNim()));
        nimCol.setPrefWidth(150);

        // Tambahkan kolom ke tabel mahasiswa
        mahasiswaTable.getColumns().addAll(namaMhsCol, nimCol);

        // Ambil data mahasiswa dari DataStore
        mahasiswaList = FXCollections.observableArrayList();
        for (User u : DataStore.getUsers()) {
            if (u instanceof Mahasiswa) {
                mahasiswaList.add((Mahasiswa) u);
            }
        }
        mahasiswaTable.setItems(mahasiswaList);

        // === Form Tambah Mahasiswa ===
        TextField namaField = new TextField();
        namaField.setPromptText("Nama Mahasiswa");
        namaField.setPrefWidth(150);

        TextField nimField = new TextField();
        nimField.setPromptText("NIM");
        nimField.setPrefWidth(150);

        // Tombol tambah mahasiswa
        Button tambahButton = new Button("Tambah");
        tambahButton.setPrefWidth(80);
        tambahButton.setOnAction(e -> {
            String nama = namaField.getText().trim();
            String nim = nimField.getText().trim();
            if (!nama.isEmpty() && !nim.isEmpty()) {
                Mahasiswa m = new Mahasiswa(nama.toLowerCase(), nim, nama, nim);
                DataStore.getUsers().add(m);
                mahasiswaList.add(m);
                namaField.clear();
                nimField.clear();
            }
        });

        // Tombol hapus mahasiswa
        Button hapusButton = new Button("Hapus");
        hapusButton.setPrefWidth(80);
        hapusButton.setOnAction(e -> {
            Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DataStore.getUsers().remove(selected);
                mahasiswaList.remove(selected);
            }
        });

        // Baris input nama + NIM + tombol tambah & hapus
        HBox inputBox = new HBox(5, namaField, nimField, tambahButton, hapusButton);
        inputBox.setAlignment(Pos.CENTER_RIGHT);

        // Kelompokkan ke dalam VBox mahasiswa
        VBox mahasiswaBox = new VBox(10, new Label("Data Mahasiswa"), mahasiswaTable, inputBox);
        mahasiswaBox.setPrefWidth(400);

        // === Gabungkan kolom laporan & mahasiswa ===
        HBox content = new HBox(20, laporanBox, mahasiswaBox);

        // === Tombol Logout ===
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            // Kembali ke LoginPane
            LoginPane loginPane = new LoginPane(stage);
            stage.setWidth(400);
            stage.setHeight(300);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.getScene().setRoot(loginPane.getPane());
        });

        // Susun semua dalam VBox utama
        VBox top = new VBox(15, welcomeLabel, content, logoutButton);
        root.setCenter(top);
    }

    // Mengembalikan layout root untuk ditampilkan di Scene
    public Pane getPane() {
        return root;
    }
}
