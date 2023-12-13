package MediTrack.Medi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import MediTrack.Medi.config.JwtTokenProvider;
import MediTrack.Medi.model.User;
import MediTrack.Medi.repository.TokenRepository;
import MediTrack.Medi.repository.UserRepository;
import MediTrack.Medi.response.AuthenticationResponse;
import MediTrack.Medi.token.Token;
import MediTrack.Medi.token.TokenType;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationResponse register(User requestUser) throws MethodArgumentNotValidException {
        // Create a BindingResult to hold the validation error
        BindingResult bindingResult = new BeanPropertyBindingResult(requestUser, "User");
        // Check if username already exists
        if (repository.findByUsername(requestUser.getUsername()) != null) {
            // Add a field error to the BindingResult
            bindingResult.addError(new FieldError("User", "username", "Username already exists"));
        }
        // Check if referenceId already exists
        if (repository.findByReferenceId(requestUser.getReferenceId()) != null) {
            bindingResult.addError(new FieldError("User", "referenceId", "Reference ID already exists"));
        }
        if (bindingResult.hasErrors()) {
            // Throw a MethodArgumentNotValidException with the BindingResult
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        var user = User.builder()
            .username(requestUser.getUsername())
            .hashedPassword(passwordEncoder.encode(requestUser.getHashedPassword()))
            .userType(requestUser.getUserType()).referenceId(requestUser.getReferenceId())
            .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtTokenProvider.generateToken(user);
        // var refreshToken = jwtTokenProvider.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            // .refreshToken(refreshToken)
            .build();
    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getHashedPassword()
            )
        );
        var user = repository.findByUsername(request.getUsername());
        var jwtToken = jwtTokenProvider.generateToken(user);
        // var refreshToken = jwtTokenProvider.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            // .refreshToken(refreshToken)
            .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(token -> {
        token.setExpired(true);
        token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, StreamWriteException, DatabindException, java.io.IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
        return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtTokenProvider.extractUsername(refreshToken);
        if (username != null) {
        var user = this.repository.findByUsername(username);
        if (jwtTokenProvider.isTokenValid(refreshToken, user)) {
            var accessToken = jwtTokenProvider.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            var authResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    //.refreshToken(refreshToken)
                    .build();
            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
        }
        }
    }
}
