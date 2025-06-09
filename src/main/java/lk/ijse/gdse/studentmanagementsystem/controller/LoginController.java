package lk.ijse.gdse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane loginAnchorpane;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if (userName.equals("user") && password.equals("1234")) {
            loginAnchorpane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/homePage.fxml"));
            loginAnchorpane.getChildren().add(load);
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
        }
    }
}
