package com.flycorp.security.controller;

import com.flycorp.lib.security.User;
import com.flycorp.security.SecurityService;
import com.flycorp.security.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private SecurityService service;

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody User dto) {

        try {
            Integer id = service.save(dto);

            if (id == null) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(id);

        } catch (Exception e) {
            log.error("user controller", e);
            return ResponseEntity.internalServerError().build();
        }

    }

}
