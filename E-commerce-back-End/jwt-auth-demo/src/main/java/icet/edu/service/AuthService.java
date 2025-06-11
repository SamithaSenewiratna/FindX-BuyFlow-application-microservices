package icet.edu.service;

import icet.edu.dto.LoginRequest;
import icet.edu.dto.LoginResponse;
import icet.edu.entity.UserEntity;
import icet.edu.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public UserEntity createUser(UserEntity userData) {
      UserEntity user = new UserEntity();
        user.setUsername(userData.getUsername());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Boolean userPresent = isUserEnable(loginRequest.getUsername());
        if (!userPresent) {
            return  new LoginResponse(null,null,"user not found","error");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        return new LoginResponse("token", LocalDateTime.now(),null,"sucess");
    }
    private  Boolean isUserEnable(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
