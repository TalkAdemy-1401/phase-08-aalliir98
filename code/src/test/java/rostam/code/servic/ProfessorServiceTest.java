package rostam.code.servic;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import rostam.code.Course.Course;
import rostam.code.Course.CourseRepository;
import rostam.code.Course.CourseService;
import rostam.code.CourseStudent.CourseStudent;
import rostam.code.CourseStudent.CourseStudentRepository;
import rostam.code.Professor.Professor;
import rostam.code.Professor.ProfessorRepository;
import rostam.code.Professor.ProfessorService;
import rostam.code.Student.StudentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceTest {
    @InjectMocks
    static
    ProfessorService service;
    @InjectMocks
    static
    StudentService studentService;
    @Mock
    private ProfessorRepository professorRepository;
    @Mock
    static
    private CourseStudentRepository courseStudentRepository;
    @Test
    public void addProfessorTest() {
        Professor professor = new Professor();
        when(professorRepository.save(Mockito.any(Professor.class))).thenReturn(professor);
        service.addProfessor(professor);
        verify(professorRepository, times(1)).save(any(Professor.class));
    }

    @Test
    public void score() {
        CourseStudent cs = new CourseStudent(1,1,10);
        when(courseStudentRepository.findByCourseIdAndStudentId(1,1)).thenReturn(cs);
        int i =service.score(1,1,10);
        Assertions.assertEquals(i,cs.getCourseId());
    }

}
