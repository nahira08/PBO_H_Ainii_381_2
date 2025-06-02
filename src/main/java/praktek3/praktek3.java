package praktek3;

// Nur Aini - 381 (Praktik membuat aplikasi data mahasiswa)

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class praktek3 extends Application{
    @Override
    public void start(Stage stage) {
        TextField namaField = new TextField();
        TextField nimField = new TextField();

        RadioButton rbLaki = new RadioButton("Laki-laki");
        RadioButton rbPerempuan = new RadioButton("Perempuan");
        ToggleGroup genderGroup = new ToggleGroup();
        rbLaki.setToggleGroup(genderGroup);
        rbPerempuan.setToggleGroup(genderGroup);

        ComboBox<String> jurusanBox = new ComboBox<>();
        jurusanBox.getItems().addAll("Teknik Informatika", "Sistem Informasi", "Teknik Elektro");

        Button simpanButton = new Button("Simpan");

        //Layout pakai GridPane
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("Nama:"), 0, 0);
        grid.add(namaField, 1, 0);
        grid.add(new Label("NIM:"), 0, 1);
        grid.add(nimField, 1, 1);
        grid.add(new Label("Jenis Kelamin:"), 0, 2);
        grid.add(rbLaki, 1, 2);
        grid.add(rbPerempuan, 2, 2);
        grid.add(new Label("Jurusan:"), 0, 3);
        grid.add(jurusanBox, 1, 3);
        grid.add(simpanButton, 1, 4);

        // Event handler tombol simpan
        simpanButton.setOnAction(e -> {
            String nama = namaField.getText();
            String nim = nimField.getText();
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            String gender = (selectedGender != null) ? selectedGender.getText() : "Belum pilih";
            String jurusan = jurusanBox.getValue();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data Mahasiswa");
            alert.setHeaderText("Informasi Data");
            alert.setContentText("Nama: " + nama + "\n" + 
                     "NIM: " + nim + "\n" +
                     "Jurusan: " + jurusan);
            alert.showAndWait();
        });

        Scene scene = new Scene(grid, 400, 250);
        stage.setTitle("Form Data Mahasiswa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
