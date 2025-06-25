package lk.ijse.gdse.studentmanagementsystem.model;

import lk.ijse.gdse.studentmanagementsystem.db.DbConnection;
import lk.ijse.gdse.studentmanagementsystem.dto.CourseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseModel {
    public boolean saveCourse(CourseDto courseDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "insert into course values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseDto.getCourseId());
        preparedStatement.setString(2, courseDto.getCourseName());
        preparedStatement.setDouble(3,courseDto.getCoursePrice());
        preparedStatement.setString(4,courseDto.getCourseDuration());
        preparedStatement.setString(5,courseDto.getStudentId());

        int i = preparedStatement.executeUpdate();
        boolean isSaved =  i > 0;
        return isSaved;
    }

    public boolean updateCourse(CourseDto courseDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE student SET course_name = ?, course_payment = ?, student_birth_day = ?, course_diuration = ?, student_id = ? WHERE course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseDto.getCourseName());
        preparedStatement.setDouble(2,courseDto.getCoursePrice());
        preparedStatement.setString(3,courseDto.getCourseDuration());
        preparedStatement.setString(4,courseDto.getStudentId());
        preparedStatement.setString(5, courseDto.getCourseId());


        int i = preparedStatement.executeUpdate();
        boolean isSaved =  i > 0;
        return isSaved;
    }

    public boolean deleteCourse(String courseId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "delete from student where course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseId);

        int result = preparedStatement.executeUpdate();
        return result > 0;
    }


    public List<CourseDto> getAllCourses() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM student"; // change 'student' to 'course' if needed
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<CourseDto> courseList = new ArrayList<>();

        while (resultSet.next()) {
            CourseDto courseDto = new CourseDto(
                    resultSet.getString("course_id"),
                    resultSet.getString("course_name"),
                    resultSet.getDouble("course_payment"),
                    resultSet.getString("course_diuration"),
                    resultSet.getString("student_id")
            );
            courseList.add(courseDto);
        }

        return courseList;
    }


}
