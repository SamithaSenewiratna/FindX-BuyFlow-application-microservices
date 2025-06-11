package icet.edu.controller;

import icet.edu.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class HomeController {

    private final JWTService jwtService;

  @GetMapping("/GET")
    public String getHello(){
        return "Hello, world!";
    }

    @PostMapping("/login")
    public String postLogin() {
        return null;
    }

    @GetMapping("/username")
    public String getUsername(@RequestParam String token) {
        return jwtService.getUsername(token);
    }

}
