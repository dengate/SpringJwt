package com.amazon.auth.controllers;

import com.amazon.auth.dto.LoginDto;
import com.amazon.auth.dto.RegisterDto;
import com.amazon.auth.jwt.JwtUtil;
import com.amazon.auth.models.User;
import com.amazon.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        User user = userRepository.findUserByUserNameAndPassword(loginDto.username,loginDto.password);

        if (user == null){
            return new ResponseEntity<>("Wrong userName or password",HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(loginDto.username);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        User user = new User();
        user.setUserName(registerDto.username);
        user.setPassword(registerDto.password);
        userRepository.save(user);
        return new ResponseEntity<String>("Registered", HttpStatus.OK);
    }
}
