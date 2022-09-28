package com.example.springbootapp.controller;

import com.example.springbootapp.auth.AuthRequest;
import com.example.springbootapp.auth.AuthResponse;
import com.example.springbootapp.auth.JwtTokenUtil;
import com.example.springbootapp.user.User;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private AuthenticationManager authManager;
  private JwtTokenUtil jwtUtil;

  public LoginController(AuthenticationManager authManager, JwtTokenUtil jwtUtil) {
    this.authManager = authManager;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
    try {
      Authentication authentication = authManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getEmail(), request.getPassword())
      );

      User user = (User) authentication.getPrincipal();
      String accessToken = jwtUtil.generateAccessToken(user);
      AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

      return ResponseEntity.ok().body(response);

    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
