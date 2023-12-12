package MediTrack.Medi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import MediTrack.Medi.service.AuthenticationService;
import MediTrack.Medi.model.User;
import MediTrack.Medi.repository.UserRepository;
import MediTrack.Medi.request.AuthenticationRequest;
import MediTrack.Medi.response.AuthenticationResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


/* 
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
*/
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.authenticate(user));
    }
}
