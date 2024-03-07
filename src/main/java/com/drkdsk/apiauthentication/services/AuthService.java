package com.drkdsk.apiauthentication.services;

import com.drkdsk.apiauthentication.enums.Role;
import com.drkdsk.apiauthentication.models.User;
import com.drkdsk.apiauthentication.repositories.UserRepository;
import com.drkdsk.apiauthentication.requests.LoginRequest;
import com.drkdsk.apiauthentication.requests.RegisterRequest;
import com.drkdsk.apiauthentication.resources.AuthResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResource login(LoginRequest request)
    {
        return null;
    }

    public AuthResource register(RegisterRequest request)
    {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResource.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}

