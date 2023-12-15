package MediTrack.Medi;

import MediTrack.Medi.model.Patient;
import MediTrack.Medi.model.User;
import MediTrack.Medi.repository.PatientRepository;
import MediTrack.Medi.service.PatientService;
import MediTrack.Medi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private PatientService patientService;

    private Patient testPatient;
    private User testUser;

    @BeforeEach
    void setUp() {
        testPatient = new Patient(); // Setup test patient
        testUser = new User(); // Setup test user
        // Initialize fields of testPatient and testUser
    }

    @Test
    void getAllPatients_ReturnsListOfPatients() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(testPatient));
        List<Patient> result = patientService.getAllPatients();
        assertFalse(result.isEmpty());
        assertEquals(testPatient, result.get(0));
    }

    @Test
    void getSinglePatient_ValidId_ReturnsPatient() {
        when(patientRepository.findById(anyString())).thenReturn(Optional.of(testPatient));
        Optional<Patient> result = patientService.getSinglePatient("validId");
        assertTrue(result.isPresent());
        assertEquals(testPatient, result.get());
    }

    @Test
    void deletePatient_ValidId_ReturnsTrue() {
        when(patientRepository.existsById(anyString())).thenReturn(true);
        boolean result = patientService.deletePatient("validId");
        assertTrue(result);
    }

    @Test
    void updatePatient_ValidId_UpdatesPatient() {
        when(patientRepository.findById(anyString())).thenReturn(Optional.of(testPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

        Patient updatedPatient = patientService.updatePatient("validId", new Patient());
        assertNotNull(updatedPatient);
        // Additional assertions based on updated fields
    }

    @Test
    void addPatient_ValidUser_CreatesPatient() {
        when(userService.findUserById(anyString())).thenReturn(Optional.of(testUser));
        when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

        Patient newPatient = patientService.addPatient(new Patient(), "userId");
        assertNotNull(newPatient);
    }
}
