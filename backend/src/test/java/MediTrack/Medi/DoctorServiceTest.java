package MediTrack.Medi;

import MediTrack.Medi.model.Doctor;
import MediTrack.Medi.model.User;
import MediTrack.Medi.repository.DoctorRepository;
import MediTrack.Medi.service.DoctorService;
import MediTrack.Medi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor testDoctor;
    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testDoctor = new Doctor();
        testDoctor.setId("doctorId");
        // Set other properties of testDoctor

        testUser = new User();
        testUser.setId("userId");
        // Set other properties of testUser
    }

    @Test
    public void testGetAllDoctors() {
        when(doctorRepository.findAll()).thenReturn(Arrays.asList(testDoctor));
        List<Doctor> doctors = doctorService.getAllDoctors();
        assertFalse(doctors.isEmpty());
        assertEquals(testDoctor, doctors.get(0));
    }

    @Test
    public void testGetDoctorById() {
        when(doctorRepository.findById("doctorId")).thenReturn(Optional.of(testDoctor));
        Optional<Doctor> result = doctorService.getDoctorById("doctorId");
        assertTrue(result.isPresent());
        assertEquals(testDoctor, result.get());
    }

    @Test
    public void testAddDoctor() {
        when(userService.findUserById("userId")).thenReturn(Optional.of(testUser));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(testDoctor);

        Doctor result = doctorService.addDoctor(new Doctor(), "userId");
        assertNotNull(result);
        assertEquals(testDoctor, result);
    }

    @Test
    public void testUpdateDoctor() {
        when(doctorRepository.findById("doctorId")).thenReturn(Optional.of(testDoctor));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(testDoctor);

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setFirstName("Updated Name");
        // Set other updated properties

        Doctor result = doctorService.updateDoctor("doctorId", updatedDoctor);
        assertNotNull(result);
        assertEquals("Updated Name", result.getFirstName());
        // Assert other properties
    }

    @Test
    public void testDeleteDoctor() {
        when(doctorRepository.existsById("doctorId")).thenReturn(true);
        boolean result = doctorService.deleteDoctor("doctorId");
        assertTrue(result);
    }
}
