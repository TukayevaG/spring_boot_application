package com.example.springbootapp.exception;

public class EmailTakenException extends RuntimeException {

  public EmailTakenException(String email) {

    super(String.format("Student with email %s already taken", email));
  }
}