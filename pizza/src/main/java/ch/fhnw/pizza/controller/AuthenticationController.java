package ch.fhnw.pizza.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import ch.fhnw.pizza.security.TokenService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

  
    private final TokenService tokenService;

    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        System.out.println("Test AuthenticationController");
        if (authentication.isAuthenticated()) { //requires a valid user (created in SecurityConfig.java)
            return tokenService.generateToken(authentication);
        } else {
            System.out.println("invalid user request !");
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/safe")
    public ResponseEntity<String> showSafeContent() {
        return new ResponseEntity<>("Only a token bearer can view this content.", HttpStatus.OK);
    }

}