package lk.ijse.gdse.studentmanagementsystem.dto.Tm;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseTm {
    private String courseId;
    private String courseName;
    private String coursePrice;
    private String courseDuration;
    private String studentId;

}
