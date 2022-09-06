package rostam.code.Professor;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rostam.code.Course.CourseRepository;
import rostam.code.Student.StudentRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/professors")
public class ProfessorController {
    private ProfessorService professorService;


    @PostMapping("/add")
    public Professor addProfessor(@RequestBody Professor professor) {
        return professorService.addProfessor(professor);
    }
    @PutMapping("/score/{courseId}/{studentId}/{score}")
    public int score(@PathVariable String courseId, @PathVariable String studentId, @PathVariable String score){
       return professorService.score(Integer.parseInt(courseId),Integer.parseInt(studentId),Integer.parseInt(score));
    }
}
