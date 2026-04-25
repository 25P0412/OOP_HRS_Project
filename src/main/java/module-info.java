module oop.project.hrs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens oop.project.hrs.ui to javafx.fxml;
    exports oop.project.hrs;
    exports oop.project.hrs.ui;
}