package com.example.springbootapp.student;

import com.example.springbootapp.exception.EmailTakenException;
import com.example.springbootapp.exception.StudentCreationException;
import com.example.springbootapp.exception.StudentNotFoundException;
import com.example.springbootapp.student.dto.StudentDto;
import com.example.springbootapp.student.dto.StudentDtoWithDateOfBirth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentWithDateOfBirthMapper studentWithDateOfBirthMapper;
  private final StudentMapper studentMapper;
  private final StudentRepository studentRepository;


  public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
      StudentWithDateOfBirthMapper studentWithDateOfBirthMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
    this.studentWithDateOfBirthMapper = studentWithDateOfBirthMapper;
  }

  public List<StudentDto> getStudents() {
    List<StudentDto> studentDtos = new ArrayList<>();
    List<Student> students = studentRepository.findAll();

    return students.stream().map(s -> studentMapper.sourceToDestination(s))
        .collect(Collectors.toList());
  }

  public void addNewStudent(StudentDtoWithDateOfBirth studentDtoWithDateOfBirth) throws Exception {
    checkEmailNotExists(studentDtoWithDateOfBirth.getEmail());

    Student student = studentWithDateOfBirthMapper.destinationToSource(studentDtoWithDateOfBirth);

    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists) {
      throw new StudentNotFoundException(studentId);
    }
    studentRepository.deleteById(studentId);
  }

  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new StudentNotFoundException(studentId));

    if (name != null && name.length() > 0 && !Objects.equals(student.getFirstName(), name)) {
      student.setFirstName(name);
    }

    checkEmail(email, student);

    student.setEmail(email);

  }

  public StudentDto getStudentById(Long id) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new StudentNotFoundException(id));

    StudentDto studentDto = studentMapper.sourceToDestination(student);

    return studentDto;
  }

  private void checkEmailNotExists(String email) {
    Optional<Student> studentByEmail;

    try {
      studentByEmail = studentRepository.findStudentByEmail(email);
    } catch (Exception exception) {
      throw new StudentCreationException("Student creation error", exception);
    }

    if (studentByEmail.isPresent()) {
      throw new EmailTakenException(email);
    }
  }

  private void checkEmail(String email, Student student) {
    if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
      checkEmailNotExists(email);
    }
  }
}
