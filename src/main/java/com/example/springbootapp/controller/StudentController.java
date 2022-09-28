package com.example.springbootapp.controller;

import com.example.springbootapp.student.StudentService;
import com.example.springbootapp.student.dto.StudentDto;
import com.example.springbootapp.student.dto.StudentDtoWithDateOfBirth;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<List<StudentDto>> getStudents() {
    List<StudentDto> studentDtoList = studentService.getStudents();
    return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
  }

  @PostMapping
  public void registerNewStudent(
      @RequestBody @Valid StudentDtoWithDateOfBirth studentDtoWithDateOfBirth) throws Exception {
    studentService.addNewStudent(studentDtoWithDateOfBirth);
  }

  @DeleteMapping(path = "{studentId}")
  public void deleteStudent(@PathVariable("studentId") Long studentId) {
    studentService.deleteStudent(studentId);
  }

  @PutMapping(path = "{studentId}")
  public void updateStudent(@PathVariable("studentId") Long studentId,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String email) {
    studentService.updateStudent(studentId, name, email);
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    StudentDto studentDto = studentService.getStudentById(id);
    return new ResponseEntity<>(studentDto, HttpStatus.OK);
  }
}
