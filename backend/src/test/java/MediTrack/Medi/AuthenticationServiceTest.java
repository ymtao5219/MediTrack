package MediTrack.Medi;

import MediTrack.Medi.model.User;
import MediTrack.Medi.repository.TokenRepository;
import MediTrack.Medi.repository.UserRepository;
import MediTrack.Medi.config.JwtTokenProvider;
import MediTrack.Medi.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthenticationService authenticationService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        // Initialize testUser fields
    }

    @Test
    public void testRegister() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtTokenProvider.generateToken(any(User.class))).thenReturn("jwtToken");

        // Call the method
        var response = authenticationService.register(testUser);

        // Assert the results
        assertNotNull(response);
        assertEquals("jwtToken", response.getAccessToken());
    }

    @Test
    public void testAuthenticate() {
        // Assuming the username and password are set in testUser
        String username = testUser.getUsername();
        String password = testUser.getHashedPassword();

        // Setup mock behavior
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
                .thenReturn(null); // Mock the authentication response

        when(userRepository.findByUsername(username)).thenReturn(testUser); // Mock the user repository response

        when(jwtTokenProvider.generateToken(testUser)).thenReturn("jwtToken"); // Mock JWT token generation

        // Call the method
        var response = authenticationService.authenticate(testUser);

        // Assert the results
        assertNotNull(response);
        assertEquals("jwtToken", response.getAccessToken());
        // Additional assertions as needed
    }

}
