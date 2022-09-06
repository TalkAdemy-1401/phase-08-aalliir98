package rostam.code.Professor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rostam.code.Course.CourseRepository;
import rostam.code.CourseStudent.CourseStudent;
import rostam.code.CourseStudent.CourseStudentRepository;
import rostam.code.Student.StudentRepository;


import java.util.ArrayList;

@AllArgsConstructor
@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private CourseStudentRepository courseStudentRepository;


    public Professor addProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public int score(int courseId, int studentId, int score) {
        CourseStudent courseStudent = courseStudentRepository.findByCourseIdAndStudentId(courseId, studentId);
        courseStudent.setScore(score);
        courseStudentRepository.save(courseStudent);
        return courseStudent.getCourseId();
    }
}
