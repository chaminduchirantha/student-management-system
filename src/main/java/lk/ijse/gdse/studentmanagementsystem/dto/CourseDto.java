package lk.ijse.gdse.studentmanagementsystem.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDto {
    private String courseId;
    private String courseName;
    private double coursePrice;
    private String courseDuration;
    private String studentId;

}
