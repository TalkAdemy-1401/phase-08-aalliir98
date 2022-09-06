package rostam.code.servic;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import rostam.code.Course.Course;
import rostam.code.Course.CourseRepository;
import rostam.code.Course.CourseService;
import rostam.code.CourseStudent.CourseStudent;
import rostam.code.CourseStudent.CourseStudentRepository;
import rostam.code.Professor.Professor;
import rostam.code.Professor.ProfessorService;
import rostam.code.Student.Student;
import rostam.code.Student.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @InjectMocks
    static
    CourseService service;
    @InjectMocks
    static
    ProfessorService professorService;
    @InjectMocks
    static
    StudentService studentService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    static
    private CourseStudentRepository courseStudentRepository;

    @BeforeAll
    public static void done() {
        professorService.addProfessor(new Professor(1, "1", "1"));
        service.addCourse(new Course(1, 40, "math", 0));
        studentService.addStudent(new Student(1, 1, 1, 17, "ali", "rostam"));
        courseStudentRepository.save(new CourseStudent(1, 1, 17));
    }

    @Test
    public void addCourseTest() {
        Course course = new Course();
        when(courseRepository.save(Mockito.any(Course.class))).thenReturn(course);
        service.addCourse(course);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    public void getAllCoursesTest() {
        service.addCourse(new Course());
        ArrayList<Course> courses = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(courses);
        int i = service.getAllCourses().size();
        Assertions.assertEquals(i, courses.size());
    }

    @Test
    public void addProfessorToCourse() {
        Course course = new Course(7, 40, "math", 3);
        when(courseRepository.findByCourseId(7)).thenReturn(course);
        Course course1 = service.addProfessorToCourse(7, 3);
        Assertions.assertEquals(course.getProfessorId(), course1.getProfessorId());
    }

    @Test
    public void addStudentToCourseTest() {
        CourseStudent cs = new CourseStudent(1, 1, 0);
        when(courseStudentRepository.save(Mockito.any(CourseStudent.class))).thenReturn(cs);
        CourseStudent cs1 = service.addStudentToCourse(1, 1);
        Assertions.assertEquals(cs.getCourseId(), cs1.getCourseId());
    }

    @Test
    public void deleteStudentToCourseTest() {
        Assertions.assertEquals(service.deleteStudentToCourse(1, 1), 0);
    }

    @Test
    public void getAvgTest() {
        ArrayList<CourseStudent> css = new ArrayList<>();
        css.add(new CourseStudent(6, 1, 10));
        when(courseStudentRepository.findAllByCourseId(6)).thenReturn(css);
        float i = service.getAverage(6);
        int sum = 0;
        for (CourseStudent a : css)
            sum += a.getScore();
        Assertions.assertEquals(sum / css.size(), i);
    }
    @Test
    public void getScores(){
        List<CourseStudent>css=new ArrayList<>();
        when(courseStudentRepository.findAllByCourseIdAndScoreIsGreaterThanEqual(6,10)).thenReturn(css);
       int i = service.getScores(6,10).size();
        Assertions.assertEquals(i,css.size());

    }

}
