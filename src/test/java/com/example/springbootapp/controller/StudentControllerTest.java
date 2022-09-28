package com.example.springbootapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springbootapp.student.Student;
import com.example.springbootapp.student.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StudentControllerTest {

  private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHVzZXIiLCJpc3MiOiJDb2RlSmF2YSIsImlhdCI6MTY2NDI1MDAzOSwiZXhwIjoxNjY0MzM2NDM5fQ.o3fv156jcPbQSGc6a0c_t66LdvqjJ6ZCkA07zxy7zftU4c6_71epRyW08mw43opXwN4cjiog2F3vwoownyhV6w";
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private StudentRepository studentRepository;

  private Student student;


  @BeforeEach
  void setUp() {
    student =
        new Student("Alex", "Smith", "alexNew@mail.com", LocalDate.of(2000, Month.APRIL, 12),
            "Math", 12);
    studentRepository.save(student);

    List<Student> s = studentRepository.findAll();
  }

  @AfterEach
  void tearDown() {
    studentRepository.deleteAll();
  }

  @Test
  public void shouldGetAllEmployees() throws Exception {
    mockMvc.perform(get("/students")
            .header("authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(1))
        .andDo(print());
  }


  @Test
  void shouldCreateStudent() throws Exception {

    Student newStudent =
        new Student("New", "New", "nnn@mail.com", LocalDate.of(2000, Month.APRIL, 12),
            "Math", 12);

    mockMvc.perform(MockMvcRequestBuilders.post("/students")
            .header("authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newStudent)))
        .andExpect(status().isOk());
  }

  @Test
  void shouldDeleteStudent() throws Exception {
    long id = 2L;

    mockMvc.perform(delete("/students/{id}", id)
            .header("authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(print());
  }
}