package MediTrack.Medi;

import MediTrack.Medi.model.User;
import MediTrack.Medi.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import MediTrack.Medi.repository.UserRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private User testUser; // Use your User model that implements UserDetails
    private String testUsername = "testUser";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking your User model
        testUser = mock(User.class);
        when(testUser.getUsername()).thenReturn(testUsername);

        // Ensure the return type is of your User model
        when(userRepository.findByUsername(testUsername)).thenReturn(testUser);
    }

    @Test
    public void loadUserByUsername_ValidUsername_ReturnsUser() {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(testUsername);
        assertNotNull(userDetails);
        assertEquals(testUsername, userDetails.getUsername());
    }

}
