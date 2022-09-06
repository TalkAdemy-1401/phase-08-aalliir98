package rostam.code.Student;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("{StudentId}/changeFav/{courseId}")
    public int changeFav(@PathVariable int StudentId, @PathVariable int courseId) {
       return studentService.changeFav(StudentId, courseId);
    }

    @GetMapping("/favCourse/{courseId}")
    public int getStudents(@PathVariable int courseId) {
        return studentService.getStudentFavCourse(courseId);
    }
}
