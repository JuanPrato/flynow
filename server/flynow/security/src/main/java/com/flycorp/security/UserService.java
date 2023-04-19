package com.flycorp.security;

import com.flycorp.lib.security.UserData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserData getUserDataByUserName(String userName) {

        Optional<UserEntity> userOp = userRepository.findByUserName(userName);

        if (userOp.isEmpty()) {
            return null;
        }

        return UserData
                .builder()
                .userName(userName)
                .build();
    }

}
