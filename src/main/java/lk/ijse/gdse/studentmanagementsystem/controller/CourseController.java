package lk.ijse.gdse.studentmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.studentmanagementsystem.dto.CourseDto;
import lk.ijse.gdse.studentmanagementsystem.dto.Tm.CourseTm;
import lk.ijse.gdse.studentmanagementsystem.dto.Tm.StudentTm;
import lk.ijse.gdse.studentmanagementsystem.model.CourseModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

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
    private TableView<CourseTm> tblCourse;

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
    void clearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String courseId = txtCourseId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Course?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = courseModel.deleteCourse(courseId);
            if (isDeleted) {
                loadTableData();
//                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "course deleted").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "course not deleted").show();
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String courseId = txtCourseId.getText();
        String courseName = txtCourseName.getText();
        String paymentAmount = txtPayment.getText();
        String courseDuration = txtDuration.getText();
        String studentId = txtId.getText();

        CourseDto courseDto = new CourseDto(courseId,courseName,paymentAmount,courseDuration,studentId);
        loadTableData();

        boolean isSaved = courseModel.saveCourse(courseDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Successfully saved the course").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "course not saved").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String courseId = txtCourseId.getText();
        String courseName = txtCourseName.getText();
        String paymentAmount = txtPayment.getText();
        String courseDuration = txtDuration.getText();
        String studentId = txtId.getText();

        CourseDto courseDto = new CourseDto(courseId,courseName,paymentAmount,courseDuration,studentId);

        boolean isUpdate = courseModel.updateCourse(courseDto);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Successfully update the course").show();
            loadTableData();
        }else{
            new Alert(Alert.AlertType.ERROR, "course not update").show();
        }
    }

    @FXML
    void tblOnClick(MouseEvent event) throws SQLException, ClassNotFoundException {
        CourseTm courseTm = tblCourse.getSelectionModel().getSelectedItem();
        if (courseTm != null) {
            txtCourseId.setText(courseTm.getCourseId());
            txtCourseName.setText(courseTm.getCourseName());
            txtPayment.setText(String.valueOf(courseTm.getCoursePrice()));
            txtDuration.setText(courseTm.getCourseDuration());
            txtId.setText(courseTm.getStudentId());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            loadTableData();
        }

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        List<CourseDto> courseDtos = courseModel.getAllCourses();

        ObservableList<CourseTm> courseTms = FXCollections.observableArrayList();
        for (CourseDto courseDto : courseDtos){
            CourseTm courseTm = new CourseTm();
            courseTm.setCourseId(courseDto.getCourseId());
            courseTm.setCourseName(courseDto.getCourseName());
            courseTm.setCoursePrice(courseDto.getCoursePrice());
            courseTm.setCourseDuration(courseDto.getCourseDuration());
            courseTm.setStudentId(courseDto.getStudentId());
            courseTms.add(courseTm);
        }
        tblCourse.setItems(courseTms);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        clmPayment.setCellValueFactory(new PropertyValueFactory<>("coursePrice"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        clmStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        try {
            loadTableData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
