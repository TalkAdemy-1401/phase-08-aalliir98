package rostam.code.CourseStudent;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "course_student")
@Entity
@SuperBuilder(toBuilder = true)

public class CourseStudent {
    @Column
    private int courseId;
    @Column
    private int studentId;
    @Column
    private int score;
    @Id
    private int id;

    public CourseStudent(int courseId, int studentId, int score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
    }
}
