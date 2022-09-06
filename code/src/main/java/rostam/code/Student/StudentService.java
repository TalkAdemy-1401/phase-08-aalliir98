package rostam.code.Student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rostam.code.Course.Course;
import rostam.code.Course.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public int changeFav(int studentId, int courseId) {
        Student student = studentRepository.findByStudentId(studentId);
        student.setFavoriteStudy(courseId);
        return studentRepository.save(student).getFavoriteStudy();

    }

    public int getStudentFavCourse(int courseId) {
        return studentRepository.findAllByFavoriteStudy(courseId).size();
    }
}
