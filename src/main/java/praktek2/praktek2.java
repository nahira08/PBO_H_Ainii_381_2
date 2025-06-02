package praktek2; 

// Nur Aini - 381 (Praktik membuat kalkulator tambahan)

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class praktek2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TextField angka1 = new TextField();
        angka1.setPromptText("Masukkan angka pertama");

        TextField angka2 = new TextField();
        angka2.setPromptText("Masukkan angka kedua");

        // Membuat tombol "Tambah"
        Button tambah = new Button("Tambah");

        // Label untuk menampilkan hasil 
        Label hasilLabel = new Label();

        tambah.setOnAction(e -> {
            try {
                int a = Integer.parseInt(angka1.getText());
                int b = Integer.parseInt(angka2.getText());
                int hasil = a + b;
                hasilLabel.setText("Hasil: " + hasil);
            } catch (NumberFormatException ex) {
                hasilLabel.setText("Masukkan angka yang benar!");
            }
        });

        // Mengatur layout dengan Vbox
        VBox root = new VBox(10, angka1, angka2, tambah, hasilLabel);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Kalkulator Sederhana JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
