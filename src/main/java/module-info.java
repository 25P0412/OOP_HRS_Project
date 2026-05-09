module oop.project.hrs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens oop.project.hrs.ui to javafx.fxml;
    opens oop.project.hrs.backend to javafx.base;
    exports oop.project.hrs;
    exports oop.project.hrs.ui;
}