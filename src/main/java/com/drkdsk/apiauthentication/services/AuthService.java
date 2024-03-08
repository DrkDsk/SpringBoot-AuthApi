package com.drkdsk.apiauthentication.services;

import com.drkdsk.apiauthentication.enums.Role;
import com.drkdsk.apiauthentication.models.User;
import com.drkdsk.apiauthentication.repositories.UserRepository;
import com.drkdsk.apiauthentication.requests.LoginRequest;
import com.drkdsk.apiauthentication.requests.RegisterRequest;
import com.drkdsk.apiauthentication.resources.AuthResource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResource login(LoginRequest request)
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);

            AuthResource authResource = AuthResource.builder().token(token).build();
            authResource.setSuccess(true);

            return authResource;

        } catch (Exception exception) {

            AuthResource authResource = new AuthResource();
            authResource.setMessage("Credenciales inválidas");
            authResource.setSuccess(false);

            return authResource;
        }
    }

    public AuthResource register(RegisterRequest request)
    {
        /*Optional<User> userExists = userRepository.findByUsername(request.getUsername());

        if (userExists.isPresent()) {
            return
        }*/

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
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

