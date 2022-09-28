package com.example.springbootapp.student;

import com.example.springbootapp.student.dto.StudentDtoWithDateOfBirth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentWithDateOfBirthMapper {

  StudentDtoWithDateOfBirth sourceToDestination(Student source);

  Student destinationToSource(StudentDtoWithDateOfBirth destination);
}
