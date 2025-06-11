package icet.edu.controller;

import icet.edu.dto.LoginRequest;
import icet.edu.dto.LoginResponse;
import icet.edu.entity.UserEntity;
import icet.edu.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

     private final AuthService authService;


   @GetMapping
    public List<UserEntity> getAllUsers() {
        return authService.getAllUsers();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {

        return  authService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authService.login(loginRequest);

        if (loginResponse.getError()!= null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);

            return ResponseEntity.ok(loginResponse);

    }

}
