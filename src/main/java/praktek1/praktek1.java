package praktek1;

// Nur Aini - 381 (Praktik membuat Pesan Sapaan)

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class praktek1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("Nama");
        TextField textField = new TextField();
        Button button = new Button("Kirim");

        button.setOnAction(e -> {
            String nama = textField.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sapaan manis");
            alert.setHeaderText(null);
            alert.setContentText("Hai, " + nama + "cayangg!");
            alert.showAndWait();
        });

        VBox root = new VBox(10, label, textField, button);
        Scene scene = new Scene(root, 350, 180);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Contoh JavaFX Sederhana");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
