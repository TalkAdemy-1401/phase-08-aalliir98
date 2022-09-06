package rostam.code.servic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import rostam.code.Student.Student;
import rostam.code.Student.StudentRepository;
import rostam.code.Student.StudentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @InjectMocks
    static
    StudentService service;
    @Mock
    private StudentRepository studentRepository;
    @Test
    public void addStudentTest() {
        Student student = new Student(1,1,1,13,"1","1");
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        service.addStudent(student);
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}
