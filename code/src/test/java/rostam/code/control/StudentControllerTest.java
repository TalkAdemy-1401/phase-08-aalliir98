package rostam.code.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rostam.code.Course.Course;
import rostam.code.Course.CourseController;
import rostam.code.Course.CourseService;
import rostam.code.Student.Student;
import rostam.code.Student.StudentController;
import rostam.code.Student.StudentService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {
    @MockBean
    private static StudentService studentService ;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void addStudentTest() throws Exception {
        Student student = new Student(1,0,1,13,"ali","lastname");
        when(studentService.addStudent(Mockito.any(Student.class))).thenReturn(student);
        mockMvc.perform(post("/students/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.studentId").value("1"));

    }
    @Test
    public void addProfessorTest() throws Exception {

        when(studentService.changeFav(1, 2)).thenReturn(1);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/students/1/changeFav/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("1"));

    }
    @Test
    public void getCourseTest() throws Exception {

        when(studentService.getStudentFavCourse(1)).thenReturn(1);
        mockMvc.perform(get("/students/favCourse/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("1"));
    }
}
