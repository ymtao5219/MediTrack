package MediTrack.Medi;

import MediTrack.Medi.model.User;
import MediTrack.Medi.repository.UserRepository;
import MediTrack.Medi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        // Initialize testUser fields
    }

    @Test
    public void testCreateUser() {
        // Assuming the password field in User is named 'password'
        String rawPassword = "rawPassword";
        testUser.setHashedPassword(rawPassword);

        when(passwordEncoder.encode(rawPassword)).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = userService.createUser(testUser);

        assertNotNull(result);
        assertNotNull(result.getHashedPassword(), "The hashed password should not be null");
        assertEquals("encodedPassword", result.getHashedPassword(), "The hashed password does not match");
    }


    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(testUser, users.get(0));
    }

    @Test
    public void testFindUserById() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(testUser));
        Optional<User> result = userService.findUserById("testId");
        assertTrue(result.isPresent());
        assertEquals(testUser, result.get());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User updatedUser = new User();
        // Set updated fields for updatedUser

        User result = userService.updateUser("testId", updatedUser);
        assertNotNull(result);
        // Assert updated fields
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(testUser));
        doNothing().when(userRepository).deleteById(anyString());

        boolean result = userService.deleteUser("testId");
        assertTrue(result);
    }
}
