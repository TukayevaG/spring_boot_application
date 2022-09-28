package com.example.springbootapp.student.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class StudentDtoWithDateOfBirth {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;

  private LocalDate dateOfBirth;
  private String faculty;
  private Integer age;

  public StudentDtoWithDateOfBirth() {

  }

  ;

  public StudentDtoWithDateOfBirth(Long id, String firstName, String lastName, String email,
      LocalDate dateOfBirth, String faculty,
      Integer age) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.faculty = faculty;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
