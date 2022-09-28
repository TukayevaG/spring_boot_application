package com.example.springbootapp.student;

import com.example.springbootapp.student.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @Mapping(source = "id", target = "id")
  StudentDto sourceToDestination(Student source);

  Student destinationToSource(StudentDto destination);
}
