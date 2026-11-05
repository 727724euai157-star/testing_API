package com.apitesting.controller;
import com.apitesting.dto.*; import com.apitesting.security.JwtService; import com.apitesting.service.UserService; import jakarta.validation.Valid; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") public class AuthController {private final UserService service;private final JwtService jwt;public AuthController(UserService s,JwtService j){service=s;jwt=j;}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED) public Responses.UserView register(@Valid @RequestBody Requests.Register r){return Responses.UserView.from(service.register(r));}
 @PostMapping("/login") public Responses.Token login(@Valid @RequestBody Requests.Login r){return new Responses.Token(jwt.create(service.login(r)));}
}
