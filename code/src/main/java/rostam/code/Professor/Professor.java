package rostam.code.Professor;

import lombok.*;
import rostam.code.Course.Course;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professors")
@Entity
@Builder
public class Professor {
    @Id
    private int professorId;
    @Column
    private String firstname;
    @Column
    private String lastname;


}

