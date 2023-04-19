package com.flycorp.security.controller;

import com.flycorp.lib.security.Token;
import com.flycorp.lib.security.User;
import com.flycorp.lib.security.UserData;
import com.flycorp.security.SecurityService;
import com.flycorp.security.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private SecurityService securityService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserData> userData(@RequestParam String token) {
        Token tokenDto = securityService.validate(token);

        if (tokenDto.getToken() == null) {
            return ResponseEntity.badRequest().build();
        }

        String userName = securityService.getUserNameFromToken(token);

        UserData user = userService.getUserDataByUserName(userName);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody User dto) {

        try {
            Integer id = securityService.save(dto);

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
