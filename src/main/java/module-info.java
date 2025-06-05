module com.praktikum {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.praktikum.gui to javafx.graphics;

    exports com.praktikum.gui;
    exports com.praktikum.data;
    exports com.praktikum.users;
    exports com.praktikum.actions;
    exports com.praktikum.main;
}
