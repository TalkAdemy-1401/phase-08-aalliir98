package rostam.code.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
    Student findByStudentId(int studentId);
    List<Student> findAllByFavoriteStudy(int favCourseId);
}
