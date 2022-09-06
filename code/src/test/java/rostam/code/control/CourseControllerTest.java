package rostam.code.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import rostam.code.Course.CourseRepository;
import rostam.code.Course.CourseService;
import rostam.code.CourseStudent.CourseStudent;
import rostam.code.CourseStudent.CourseStudentRepository;
import rostam.code.Student.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CourseController.class)
public class CourseControllerTest {

    @MockBean
    private static CourseService courseService ;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getCourseTest() throws Exception {
        Course course1 = new Course();
        Course course2 = new Course();
        when(courseService.getAllCourses()).thenReturn(new ArrayList<>(Arrays.asList(course1, course2)));
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.size()", Matchers.is(2)));
    }

    @Test
    public void addCourseTest() throws Exception {
        Course course1 = new Course(1, 40, "math", 23);
        when(courseService.addCourse(Mockito.any(Course.class))).thenReturn(course1);
        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(course1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.courseId").value("1"));

    }

    @Test
    public void addProfessorTest() throws Exception {
        Course course1 = new Course(1, 40, "math", 23);
        when(courseService.addProfessorToCourse(1, 2)).thenReturn(course1);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/courses/1/addProfessor/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(course1));

        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("23")).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(contentAsString);
    }

    @Test
    public void addStudentTest() throws Exception {
        CourseStudent cs = new CourseStudent(1, 1, 1);
        when(courseService.addStudentToCourse(1, 1)).thenReturn(cs);
        mockMvc.perform(post("/courses/1/addStudent/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cs))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("1"));
    }

    @Test
    public void deleteStudent() throws Exception {
        when(courseService.deleteStudentToCourse(1, 1)).thenReturn(1);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete("/courses/1/deleteStudent/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    public void getAverageTest() throws Exception {
        when(courseService.getAverage(1)).thenReturn(1.0F);
        mockMvc.perform(get("/courses/average/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("1.0"));
    }

    @Test
    public void getScoresTest() throws Exception {
        List<Float> a = new ArrayList<>();
        a.add(1F);
        when(courseService.getScores(1, 10)).thenReturn(a);
        mockMvc.perform(get("/courses/1/score/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()").value(1));
    }

}
