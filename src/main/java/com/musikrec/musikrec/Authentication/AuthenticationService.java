package com.musikrec.musikrec.Authentication;

import com.musikrec.musikrec.Authentication.Dto.AuthenticationRequest;
import com.musikrec.musikrec.Authentication.Dto.AuthenticationResponse;
import com.musikrec.musikrec.Authentication.Dto.RegisterRequest;
import com.musikrec.musikrec.Exceptions.CustomExceptions.AuthenticationException;
import com.musikrec.musikrec.Exceptions.CustomExceptions.DuplicatedResourceException;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Integration.SpotifyAPI.SpotifyService;
import com.musikrec.musikrec.Security.JWT.JwtService;
import com.musikrec.musikrec.User.AppUser;
import com.musikrec.musikrec.User.AppUserRepository;
import com.musikrec.musikrec.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SpotifyService spotifyService;

    public AuthenticationResponse register(RegisterRequest request) throws AuthenticationException {
        Optional<AppUser> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicatedResourceException("Username already used.");
        } else {
            AppUser user = AppUser.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            var jwtToken = "Bearer " + jwtService.generateToken(user);

            AuthenticationResponse response = new AuthenticationResponse(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    jwtToken,
                    true,
                    "Bearer " + spotifyService.getAccessToken()
            );

            return response;
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found during authentication."));

        var jwtToken = "Bearer " + jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                jwtToken,
                true,
                "Bearer " +spotifyService.getAccessToken()
        );

        return response;
    }
}
