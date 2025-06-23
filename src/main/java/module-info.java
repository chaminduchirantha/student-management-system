module lk.ijse.gdse.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;


    opens lk.ijse.gdse.studentmanagementsystem.controller to javafx.fxml;
    exports lk.ijse.gdse.studentmanagementsystem;
}