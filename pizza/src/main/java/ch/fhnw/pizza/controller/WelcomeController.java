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

public class WelcomeController {

    @GetMapping(value="/")
    public String getWelcomeString() {
        
        return "Hello, welcome to our Pizzeria!";
    }

    @GetMapping(value="/user")
    public String getUserRole(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String role = userDetails.getAuthorities().toArray()[1].toString();
        return role;
    }

 
    private final TokenService tokenService;

    public WelcomeController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        if (authentication.isAuthenticated()) { //requires a valid user (created in SecurityConfig.java)
            return tokenService.generateToken(authentication);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/safe")
    public ResponseEntity<String> showSafeContent() {
        return new ResponseEntity<>("Only a token bearer can view this content.", HttpStatus.OK);
    }

}