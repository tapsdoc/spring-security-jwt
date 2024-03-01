package com.example.Spring.SecurityAlibou.service.auth;

import com.example.Spring.SecurityAlibou.commons.Role;
import com.example.Spring.SecurityAlibou.commons.TokenType;
import com.example.Spring.SecurityAlibou.domain.Token;
import com.example.Spring.SecurityAlibou.domain.User;
import com.example.Spring.SecurityAlibou.exception.domain.UserNotFoundException;
import com.example.Spring.SecurityAlibou.repository.TokenRepository;
import com.example.Spring.SecurityAlibou.repository.UserRepository;
import com.example.Spring.SecurityAlibou.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final TokenRepository tokenRepository;

    @Override
    public AuthResponse register(RegistrationRequest request) {

        User user = User.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .role(Role.USER)
            .build();

        userRepository.save(user);
        return getAuthResponse(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) throws UserNotFoundException {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );

        var user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return getAuthResponse(user);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, UserNotFoundException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) return;

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            User user = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllTokens(user);
                var token = Token.builder()
                    .user(user)
                    .token(accessToken)
                    .tokenType(TokenType.BEARER)
                    .expired(false)
                    .revoked(false)
                    .build();
                tokenRepository.save(token);
                var authResponse = AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

                new ObjectMapper().writeValue(
                    response.getOutputStream(),
                    authResponse
                );
            }

        }
    }

    private void revokeAllTokens(User user) {
        var validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);
    }

    private AuthResponse getAuthResponse(User user) {
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllTokens(user);
        var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
        return AuthResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }
}
