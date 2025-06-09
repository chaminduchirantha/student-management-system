module lk.ijse.gdse.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.gdse.studentmanagementsystem.controller to javafx.fxml;
    exports lk.ijse.gdse.studentmanagementsystem;
}