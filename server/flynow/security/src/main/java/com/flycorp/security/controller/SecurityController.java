package com.flycorp.security.controller;

import com.flycorp.lib.security.Token;
import com.flycorp.lib.security.User;
import com.flycorp.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/security")
@AllArgsConstructor
public class SecurityController {

    private SecurityService service;

    @PostMapping
    public ResponseEntity<Token> login(@RequestBody User dto) {

        Token token = service.login(dto);

        if (token == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(token);
    }

    @GetMapping("validate")
    public ResponseEntity<Token> validate(@RequestParam String token) {
        Token tokenDto = service.validate(token);

        if (token == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tokenDto);
    }

}
