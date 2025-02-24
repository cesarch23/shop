package com.edu.shop.controller;

import com.edu.shop.config.JwtUtil;
import com.edu.shop.dto.LoginDTO;
import com.edu.shop.dto.UserDTO;
import com.edu.shop.serviceImpls.UserSecurityServiceImpl;
import com.edu.shop.serviceImpls.UserServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    public AuthController(AuthenticationManager authenticationManager, UserServiceImpl userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(path = "/login")
    ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getMail(),loginDTO.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getPrincipal());

        String token = this.jwtUtil.createToken(loginDTO.getMail());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).build() ;
    }
    @PostMapping(path = "/register")
    ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.register(userDTO));
    }
}
