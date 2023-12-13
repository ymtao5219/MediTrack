package MediTrack.Medi;

import MediTrack.Medi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import MediTrack.Medi.controller.PatientController;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.service.PatientService;
import MediTrack.Medi.service.UserService;
import MediTrack.Medi.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @Mock
    private UserService userService;

    @Mock
    private PatientRepository patientRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    public void testGetAllPatients() throws Exception {
        // Mock the service to return a list of patients
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        when(patientService.getAllPatients()).thenReturn(patients);

        // Perform GET request to /patients
        mockMvc.perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(patients.size()));
    }

    @Test
    public void testGetSinglePatient() throws Exception {
        // Mock the service to return a single patient
        Patient patient = new Patient();
        patient.setId("1");
        when(patientService.getSinglePatient("1")).thenReturn(Optional.of(patient));

        // Perform GET request to /patients/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    public void testDeletePatient() throws Exception {
        // Mock the service to return a patient to delete
        Patient patient = new Patient();
        patient.setId("1");
        when(patientService.getSinglePatient("1")).thenReturn(Optional.of(patient));

        // Perform DELETE request to /patients/{id}
        mockMvc.perform(MockMvcRequestBuilders.delete("/patients/1"))
                .andExpect(status().isNoContent());

        // Verify that the service's deletePatient method was called
        verify(patientService, times(1)).deletePatient("1");
    }
}
