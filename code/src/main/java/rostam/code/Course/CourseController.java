package rostam.code.Course;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rostam.code.CourseStudent.CourseStudent;
import rostam.code.Student.Student;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/courses")
public class CourseController {
    private CourseService courseService;

    @PostMapping(value = "/add")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("{courseId}/addProfessor/{professorId}")
    public int addProfessor(@PathVariable int courseId, @PathVariable int professorId) {
        Course body = courseService.addProfessorToCourse(courseId, professorId);
        return body.getProfessorId();
    }

    @PostMapping("{courseId}/addStudent/{StudentId}")
    public int addStudent(@PathVariable int StudentId, @PathVariable int courseId) {
        CourseStudent body = courseService.addStudentToCourse(StudentId, courseId);
        return body.getCourseId();
    }

    @DeleteMapping("{courseId}/deleteStudent/{StudentId}")
    public int deleteStudent(@PathVariable int StudentId, @PathVariable int courseId) {
        return courseService.deleteStudentToCourse(StudentId, courseId);
    }

    @GetMapping("average/{courseId}")
    public Float getAverage(@PathVariable int courseId) {
        return courseService.getAverage(courseId);
    }

    @GetMapping("{courseId}/score/{score}")
    public List<Float> getScores(@PathVariable int score, @PathVariable int courseId) {
        return courseService.getScores(courseId, score);
    }

    @GetMapping("")
    public List<Course> getAll() {
        return courseService.getAllCourses();
    }
}
