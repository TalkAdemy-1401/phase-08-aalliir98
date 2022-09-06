package rostam.code.Course;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rostam.code.Student.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Entity
@SuperBuilder(toBuilder = true)
public class Course {
    @Id
    private int courseId;
    @Column
    private int capacity;
    @Column
    private String courseName;
    @Column
    private int professorId;
}
