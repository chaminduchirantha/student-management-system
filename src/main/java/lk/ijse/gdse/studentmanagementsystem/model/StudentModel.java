package lk.ijse.gdse.studentmanagementsystem.model;

import lk.ijse.gdse.studentmanagementsystem.db.DbConnection;
import lk.ijse.gdse.studentmanagementsystem.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    public boolean saveStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "insert into student values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentDto.getStudentId());
        preparedStatement.setString(2, studentDto.getStudentName());
        preparedStatement.setString(3, studentDto.getStudentAge());
        preparedStatement.setString(4, studentDto.getStudentBirthDay());
        preparedStatement.setString(5, studentDto.getStudentAddress());
        preparedStatement.setString(6, studentDto.getStudentContactNumber());

        int i = preparedStatement.executeUpdate();
        boolean isSaved = i>0;
        return isSaved;
    }

    public boolean updateStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE student SET student_name = ?, student_age = ?, student_birth_day = ?, student_address = ?, student_contact_number = ? WHERE student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentDto.getStudentName());
        preparedStatement.setString(2, studentDto.getStudentAge());
        preparedStatement.setString(3, studentDto.getStudentBirthDay());
        preparedStatement.setString(4, studentDto.getStudentAddress());
        preparedStatement.setString(5, studentDto.getStudentContactNumber());
        preparedStatement.setString(6, studentDto.getStudentId());

        int i = preparedStatement.executeUpdate();
        boolean isUpdate = i>0;
        return isUpdate;
    }

    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "delete from student where student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentId);
        int i = preparedStatement.executeUpdate();

        boolean isDeleted = i>0;
        return isDeleted;
    }

    public List<StudentDto> getAllStudent() throws SQLException, ClassNotFoundException {
       Connection connection = DbConnection.getInstance().getConnection();
       String sql = "select * from student";
       PreparedStatement preparedStatement = connection.prepareStatement(sql);
       ResultSet resultSet = preparedStatement.executeQuery();

        List<StudentDto> studentDtos = new ArrayList<>();

           while (resultSet.next()) {
               StudentDto studentDto = new StudentDto();
               studentDto.setStudentId(resultSet.getString("student_id"));
               studentDto.setStudentName(resultSet.getString("student_name"));
               studentDto.setStudentAge(resultSet.getString("student_age"));
               studentDto.setStudentBirthDay(resultSet.getString("student_birth_day"));
               studentDto.setStudentAddress(resultSet.getString("student_address"));
               studentDto.setStudentContactNumber(resultSet.getString("student_contact_number"));

               studentDtos.add(studentDto);

           }
       return studentDtos;
    }
}
