module com.praktikum {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.praktikum to javafx.fxml;
    exports com.praktikum;
    exports codelab;
    exports praktek1;
    exports praktek2;
    exports praktek3;
}
