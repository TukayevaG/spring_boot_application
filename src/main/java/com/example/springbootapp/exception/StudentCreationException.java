package com.example.springbootapp.exception;

public class StudentCreationException extends RuntimeException {

  public StudentCreationException(String message, Throwable cause) {
    super(message, cause);
  }
}
