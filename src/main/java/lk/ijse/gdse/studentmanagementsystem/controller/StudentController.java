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
import lk.ijse.gdse.studentmanagementsystem.dto.StudentDto;
import lk.ijse.gdse.studentmanagementsystem.dto.Tm.StudentTm;
import lk.ijse.gdse.studentmanagementsystem.model.StudentModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> cclmAddress;

    @FXML
    private TableColumn<?, ?> clmAge;

    @FXML
    private TableColumn<?, ?> clmBirthDay;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPhone;

    @FXML
    private AnchorPane studentAnchorpane;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    StudentModel studentModel = new StudentModel();

    @FXML
    void clearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Student?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = studentModel.deleteStudent(studentId);
            if (isDeleted) {
                loadTableData();
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "student deleted").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "student not deleted").show();
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        String studentName = txtName.getText();
        String studentAge = txtAge.getText();
        String studentBirthDay = txtDate.getValue().toString();
        String studentAddress = txtAddress.getText();
        String studentPhone = txtContact.getText();

        StudentDto studentDto = new StudentDto(studentId, studentName, studentAge, studentBirthDay, studentAddress, studentPhone);

        boolean isSaved = studentModel.saveStudent(studentDto);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Student").show();
            loadTableData();
            refreshPage();
        }else{
            new Alert(Alert.AlertType.ERROR, "Fail to save the Student").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String studentId = txtId.getText();
        String studentName = txtName.getText();
        String studentAge = txtAge.getText();
        String studentBirthDay = txtDate.getValue().toString();
        String studentAddress = txtAddress.getText();
        String studentPhone = txtContact.getText();

        StudentDto studentDto = new StudentDto(studentId, studentName, studentAge, studentBirthDay, studentAddress, studentPhone);

        boolean isUpdate = studentModel.updateStudent(studentDto);

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION, "Successfully updated the Student").show();
            loadTableData();
            refreshPage();
        }else{
            new Alert(Alert.AlertType.ERROR, "Fail to update the Student").show();
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        List<StudentDto> studentDtos = studentModel.getAllStudent();

        ObservableList<StudentTm> studentTms = FXCollections.observableArrayList();
        for (StudentDto studentDto : studentDtos){
            StudentTm studentTm = new StudentTm();
            studentTm.setStudentId(studentDto.getStudentId());
            studentTm.setStudentName(studentDto.getStudentName());
            studentTm.setStudentAge(studentDto.getStudentAge());
            studentTm.setStudentBirthDay(studentDto.getStudentBirthDay());
            studentTm.setStudentAddress(studentDto.getStudentAddress());
            studentTm.setStudentContactNumber(studentDto.getStudentContactNumber());
            studentTms.add(studentTm);
        }
        tblStudent.setItems(studentTms);
    }

    @FXML
    void onClickTable(MouseEvent event) throws SQLException, ClassNotFoundException {
        StudentTm studentTm = tblStudent.getSelectionModel().getSelectedItem();
        if (studentTm != null) {
            txtId.setText(studentTm.getStudentId());
            txtName.setText(studentTm.getStudentName());
            txtAge.setText(String.valueOf(studentTm.getStudentAge()));
            txtDate.setValue(LocalDate.parse(studentTm.getStudentBirthDay()));
            txtAddress.setText(studentTm.getStudentAddress());
            txtContact.setText(studentTm.getStudentContactNumber());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            loadTableData();
        }
    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        txtDate.setTooltip(new Tooltip("date"));
        txtContact.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        clmAge.setCellValueFactory(new PropertyValueFactory<>("studentAge"));
        clmBirthDay.setCellValueFactory(new PropertyValueFactory<>("studentBirthDay"));
        cclmAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<>("studentContactNumber"));
        try {
            loadTableData();
            refreshPage();
        }catch (Exception e){

        }
    }
}
