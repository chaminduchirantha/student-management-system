package lk.ijse.gdse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.studentmanagementsystem.dto.CourseDto;
import lk.ijse.gdse.studentmanagementsystem.model.CourseModel;

import java.sql.SQLException;

public class CourseController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmCourseId;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPayment;

    @FXML
    private TableColumn<?, ?> clmStudentID;

    @FXML
    private AnchorPane courseAnchorpane;

    @FXML
    private TableView<?> tblCourse;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPayment;


    CourseModel courseModel = new CourseModel();

    @FXML
    void clearOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String courseId = txtCourseId.getText();
        String courseName = txtCourseName.getText();
        String paymentAmount = txtPayment.getText();
        String courseDuration = txtDuration.getText();
        String studentId = txtId.getText();

        CourseDto courseDto = new CourseDto(courseId,courseName,paymentAmount,courseDuration,studentId);

        boolean isSaved = courseModel.saveCourse(courseDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Successfully saved the course").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "course not saved").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
