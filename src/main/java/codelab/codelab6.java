package codelab;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class codelab6 extends Application {

    private int angkaAcak;
    private int jumlahPercobaan = 0;
    private final int batasPercobaan = 6;

    private Label feedbackLabel;
    private Label percobaanLabel;
    private TextField inputField;
    private Button tombolAksi;

    @Override
    public void start(Stage stage) {
        generateAngkaBaru();

        // Judul si tebak angka yaw
        Label judulLabel = new Label("🦊 Tebak Angka 1–100 ✨");
        judulLabel.setFont(Font.font("Palatino Linotype", 25));
        judulLabel.setTextFill(Color.web("#c2185b"));

        // Feedback awalnyaaa
        feedbackLabel = new Label("Masukkan tebakanmu!");
        feedbackLabel.setFont(Font.font("Palatino Linotype", 14));
        feedbackLabel.setTextFill(Color.GRAY);

        // Input dan tombolnyaa
        inputField = new TextField();
        inputField.setPromptText("Masukkan angka di sini");
        inputField.setMaxWidth(200);

        tombolAksi = new Button("Coba Tebak!");
        tombolAksi.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        tombolAksi.setOnAction(e -> handleTebakan());

        HBox inputArea = new HBox(10, inputField, tombolAksi);
        inputArea.setAlignment(Pos.CENTER);

        // Label jumlah percobaannyaa
        percobaanLabel = new Label("Jumlah percobaan: 0");
        percobaanLabel.setFont(Font.font("Palatino Linotype", 13));
        percobaanLabel.setTextFill(Color.GRAY);

        // Layout utamanyaaa
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color:rgb(253, 234, 255);");

        root.getChildren().addAll(judulLabel, feedbackLabel, inputArea, percobaanLabel);

        // Scene buat tebak angkanyaa
        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("Tebak Angka Advance");
        stage.setScene(scene);
        stage.show();
    }

    private void handleTebakan() {
        if (tombolAksi.getText().equals("🔁 Main Lagi")) {
            resetGame();
            return;
        }

        try {
            int tebakan = Integer.parseInt(inputField.getText());
            jumlahPercobaan++;
            percobaanLabel.setText("Jumlah percobaan: " + jumlahPercobaan);

            if (tebakan < angkaAcak) {
                feedbackLabel.setText("🔽 Terlalu kecil!");
                feedbackLabel.setTextFill(Color.ORANGE);
            } else if (tebakan > angkaAcak) {
                feedbackLabel.setText("🔼 Terlalu besar!");
                feedbackLabel.setTextFill(Color.ORANGE);
            } else {
                feedbackLabel.setText("✅ Tebakan benar!");
                feedbackLabel.setTextFill(Color.GREEN);
                tombolAksi.setText("🔁 Main Lagi");
                tombolAksi.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
                inputField.setDisable(true);
                return;
            }

            if (jumlahPercobaan >= batasPercobaan) {
                feedbackLabel.setText("❌ Kesempatan habis! Angka yang benar adalah: " + angkaAcak);
                feedbackLabel.setTextFill(Color.RED);
                tombolAksi.setText("🔁 Main Lagi");
                tombolAksi.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
                inputField.setDisable(true);
            }

        } catch (NumberFormatException e) {
            feedbackLabel.setText("⚠️ Masukkan angka valid!");
            feedbackLabel.setTextFill(Color.RED);
        }
    }

    private void resetGame() {
        generateAngkaBaru();
        jumlahPercobaan = 0;
        percobaanLabel.setText("Jumlah percobaan: 0");
        feedbackLabel.setText("Masukkan tebakanmu!");
        feedbackLabel.setTextFill(Color.GRAY);
        inputField.clear();
        inputField.setDisable(false);
        tombolAksi.setText("Coba Tebak!");
        tombolAksi.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
    }

    private void generateAngkaBaru() {
        angkaAcak = new Random().nextInt(100) + 1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
