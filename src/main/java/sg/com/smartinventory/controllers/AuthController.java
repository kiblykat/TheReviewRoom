package sg.com.smartinventory.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.smartinventory.entities.LoginRequest;
import sg.com.smartinventory.entities.User;
import sg.com.smartinventory.security.JwtService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth/")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private JwtService jwtService;

  public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest login) {
    Authentication auth = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

    // Get principal name of authenticated obj.
    String username = auth.getName();

    // Generate JWT token from authenticated obj.
    String token = jwtService.createToken(new User(username));

    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}