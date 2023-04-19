package com.flycorp.security;

import com.flycorp.lib.security.Token;
import com.flycorp.lib.security.User;
import com.flycorp.security.config.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    public Integer save(User userDto) {
        Optional<UserEntity> user = userRepository.findByUserName(userDto.getUserName());
        if (user.isPresent()) {
            //TODO: Throw userName already exists exception
            return null;
        }

        String password = passwordEncoder.encode(userDto.getPassword());
        UserEntity userEntity = UserEntity
                .builder()
                .userName(userDto.getUserName())
                .password(password)
                .build();

        return userRepository.saveAndFlush(userEntity).getId();
    }

    public Token login(User userDto) {
        Optional<UserEntity> user = userRepository.findByUserName(userDto.getUserName());

        if (user.isEmpty()) {
            return null;
        }

        if (!passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
            return null;
        }

        return Token
                .builder()
                .token(
                    jwtProvider.createToken(user.get())
                )
                .build();
    }

    public Token validate(String token) {

        if (!jwtProvider.validate(token)) {
            return Token.builder().token(null).build();
        }

        String userName = jwtProvider.getUserNameFromToken(token);

        if (userRepository.findByUserName(userName).isEmpty()) {
            return Token.builder().token(null).build();
        }

        return Token.builder().token(token).build();
    }

    public String getUserNameFromToken(String token) {
        return jwtProvider.getUserNameFromToken(token);
    }

}
