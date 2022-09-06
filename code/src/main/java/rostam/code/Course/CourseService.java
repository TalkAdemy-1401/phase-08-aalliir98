package rostam.code.Course;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rostam.code.CourseStudent.CourseStudent;
import rostam.code.CourseStudent.CourseStudentRepository;
import rostam.code.Professor.Professor;
import rostam.code.Student.Student;
import rostam.code.Student.StudentRepository;

import java.util.*;

@AllArgsConstructor
@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CourseStudentRepository courseStudentRepository;

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course addProfessorToCourse(int courseId, int professorId) {
        Course course = courseRepository.findByCourseId(courseId);
        course.setProfessorId(professorId);
        courseRepository.save(course);
        return course;
    }

    public CourseStudent addStudentToCourse(int studentId, int courseId) {
        CourseStudent courseStudent = new CourseStudent(courseId, studentId, -1);
        courseStudentRepository.save(courseStudent);
        return courseStudent;
    }

    public int deleteStudentToCourse(int studentId, int courseId) {
        ArrayList<CourseStudent> css = new ArrayList<>();
        courseStudentRepository.findAll().forEach(css::add);
        CourseStudent courseStudent = courseStudentRepository.findByCourseIdAndStudentId(courseId, studentId);
        courseStudentRepository.delete(courseStudent);
        ArrayList<CourseStudent> css2 = new ArrayList<>();
        courseStudentRepository.findAll().forEach(css::add);
        if (css2.size() == css.size())
            return 0;
        else return 1;
    }

    public Float getAverage(int courseId) {
        List<CourseStudent> courseStudents = courseStudentRepository.findAllByCourseId(courseId);
        float sum = 0, num = 0;
        for (CourseStudent a : courseStudents) {
            sum += a.getScore();
            num++;
        }
        return (sum / num);
    }

    public List<Float> getScores(int courseId, int score) {
        List<CourseStudent> courseStudents = courseStudentRepository.findAllByCourseIdAndScoreIsGreaterThanEqual(courseId, score);
        List<Float> scores = new ArrayList<>();
        for (CourseStudent a : courseStudents)
            scores.add((float) a.getScore());
        return scores;
    }
}
