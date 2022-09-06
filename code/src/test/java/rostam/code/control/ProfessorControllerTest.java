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
import rostam.code.Professor.Professor;
import rostam.code.Professor.ProfessorController;
import rostam.code.Professor.ProfessorService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProfessorController.class)
public class ProfessorControllerTest {
    @MockBean
    private ProfessorService professorService;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addProfessorTest() throws Exception {
        Professor pf = new Professor(1, "ali", "rostam");

        when(professorService.addProfessor(Mockito.any(Professor.class))).thenReturn(pf);
        mockMvc.perform(post("/professors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pf))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.professorId").value("1"));

    }

    @Test
    public void scoreTest() throws Exception {
        when(professorService.score(1, 1, 10)).thenReturn(1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/professors/score/1/1/10")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }
}
