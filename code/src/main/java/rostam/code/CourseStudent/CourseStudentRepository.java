package rostam.code.CourseStudent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseStudentRepository extends CrudRepository<CourseStudent, Integer> {
    CourseStudent findByCourseIdAndStudentId(int courseId, int studentId);
@Transactional
    List<CourseStudent> findAllByCourseId(int courseId);
    List<CourseStudent> findAllByCourseIdAndScoreIsGreaterThanEqual(int courseId, int score);
}
