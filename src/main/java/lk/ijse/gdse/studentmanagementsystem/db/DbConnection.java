package lk.ijse.gdse.studentmanagementsystem.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system");
    }
        public static DbConnection getInstance() throws ClassNotFoundException, SQLException {
            if (dbConnection == null) {
                dbConnection = new DbConnection();
            }
            return dbConnection;
        }
    }
