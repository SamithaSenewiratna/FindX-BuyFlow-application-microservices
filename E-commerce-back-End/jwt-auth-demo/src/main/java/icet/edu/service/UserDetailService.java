package icet.edu.service;

import icet.edu.entity.UserEntity;
import icet.edu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;



public class UserDetailService implements UserDetailsService {


    private final UserRepository repository;

    public UserDetailService( UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

       UserDetails user = User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
//                .roles("USER")
                .build();
        return user;
    }
}
