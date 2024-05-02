package com.hackathon.mobil.rest;

import com.hackathon.mobil.dto.AuthRequest;
import com.hackathon.mobil.dto.AuthResponse;
import com.hackathon.mobil.dto.CreateUserRequest;
import com.hackathon.mobil.entity.User;
import com.hackathon.mobil.service.JwtService;
import com.hackathon.mobil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping({"/PUBLIC/username"})
    public User getByUserUserName(@RequestParam String username){
        return userService.getUserByUserName(username).orElseThrow();
    }
    @PutMapping("/PUBLIC")
    public User updateUser(@RequestBody User user){return userService.insert(user);}
    @PostMapping("/PUBLIC/addNewUser")
    public ResponseEntity<String> addUser(@RequestBody CreateUserRequest request) throws Exception {
        return  userService.createUser(request);
    }
    @PostMapping("/PUBLIC/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        log.info("invalid username " + request.username());
        throw new UsernameNotFoundException("invalid username {} " + request.username());
    }
    @PostMapping("/PUBLIC/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        if (authentication.isAuthenticated()) {
            String username = authRequest.username();
            UserDetails userDetails = userService.loadUserByUsername(username);
            String token = jwtService.generateToken(username);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken("Bearer " + token);
            authResponse.setUserId(userDetails.getUsername());
            authResponse.setRefreshToken("RefreshToken");
            System.out.println(token);
            return authResponse;
        } else {
            throw new RuntimeException("Authentication failed");
        }

    }

}
