package lk.ijse.gdse.studentmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String studentId;
    private String studentName;
    private String studentAge;
    private String studentBirthDay;
    private String studentAddress;
    private String studentContactNumber;

}
