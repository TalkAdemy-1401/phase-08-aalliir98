package rostam.code.Student;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@Entity
@Builder
public class Student {
    @Id
    private int studentId;
    @Column
    private int probationaryStatus;
    @Column
    private int favoriteStudy;
    @Column
    private float GPA;
    @Column
    private String firstname;
    @Column
    private String lastname;
}