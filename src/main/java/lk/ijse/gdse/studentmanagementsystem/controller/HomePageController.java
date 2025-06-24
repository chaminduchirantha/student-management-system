package lk.ijse.gdse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class HomePageController {

    @FXML
    private Button btnAttendance;

    @FXML
    private Button btnCOURSE;

    @FXML
    private Button btnDepartment;

    @FXML
    private Button btnLec;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnSalary;

    @FXML
    private Button btnStudent;

    @FXML
    private AnchorPane homeAnchorpane;


    @FXML
    private AnchorPane loadingAnchorpane;

    @FXML
    void attendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutOnActoin(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Log out this Programme?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = true;
            if (isDeleted) {
                homeAnchorpane.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/View/loginView.fxml"));
                homeAnchorpane.getChildren().add(load);
            } else {
                new Alert(Alert.AlertType.ERROR, "Log out not Programme").show();
            }
        }
    }

    @FXML
    void courseOnAction(ActionEvent event) {
        navigateTo("/view/courseView.fxml");

    }

    @FXML
    void departmentOnAction(ActionEvent event) {

    }

    @FXML
    void lectuersOnAction(ActionEvent event) {
        navigateTo("/view/lectuersView.fxml");

    }

    @FXML
    void paymentOnActition(ActionEvent event) {

    }

    @FXML
    void salaryOnAction(ActionEvent event) {

    }

    @FXML
    void studentOnAction(ActionEvent event) {
        navigateTo("/view/studentView.fxml");

    }


    private void navigateTo(String fxmlPath) {
        try {
            loadingAnchorpane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane loadedPane = loader.load();
            loadingAnchorpane.getChildren().add(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load view: " + fxmlPath);
        }
        System.out.println("Loading FXML: " + fxmlPath);
    }

}
