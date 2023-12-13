package MediTrack.Medi;

import MediTrack.Medi.model.Appointment;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.repository.AppointmentRepository;
import MediTrack.Medi.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation.ExecutableUpdate;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation.TerminatingUpdate;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation.UpdateWithUpdate;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private AppointmentService appointmentService;

    private Appointment testAppointment;
    private Patient testPatient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testAppointment = new Appointment();
        // Initialize testAppointment fields
        testPatient = new Patient();
        // Initialize testPatient fields
    }

    @Test
    public void testCreateAppointment() {
        when(appointmentRepository.insert(any(Appointment.class))).thenReturn(testAppointment);

        // Mocking the behavior of MongoTemplate for createAppointment
        ExecutableUpdate<Patient> executableUpdate = mock(ExecutableUpdate.class);
        UpdateWithUpdate<Patient> updateWithUpdate = mock(UpdateWithUpdate.class);
        TerminatingUpdate<Patient> terminatingUpdate = mock(TerminatingUpdate.class);

        when(mongoTemplate.update(Patient.class)).thenReturn(executableUpdate);
        when(executableUpdate.matching(any(Criteria.class))).thenReturn(updateWithUpdate);
        when(updateWithUpdate.apply(any(Update.class))).thenReturn(terminatingUpdate);

        Appointment result = appointmentService.createAppointment(testAppointment, "patientId");
        assertNotNull(result);
    }

    @Test
    public void testGetAllAppointment() {
        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(testAppointment));
        List<Appointment> appointments = appointmentService.getAllAppointment();
        assertFalse(appointments.isEmpty());
        assertEquals(1, appointments.size());
        assertEquals(testAppointment, appointments.get(0));
    }

    @Test
    public void testGetSingleAppointment() {
        when(appointmentRepository.findById("appointmentId")).thenReturn(Optional.of(testAppointment));
        Optional<Appointment> result = appointmentService.getSingleAppointment("appointmentId");
        assertTrue(result.isPresent());
        assertEquals(testAppointment, result.get());
    }

    @Test
    public void testUpdateAppointment() {
        when(appointmentRepository.findById("appointmentId")).thenReturn(Optional.of(testAppointment));

        // Mock the behavior of MongoTemplate for update
        UpdateResult mockUpdateResult = mock(UpdateResult.class);
        when(mockUpdateResult.getModifiedCount()).thenReturn(1L); // Assuming the update is successful
        when(mongoTemplate.updateFirst(any(Query.class), any(Update.class), eq(Patient.class))).thenReturn(mockUpdateResult);

        boolean success = appointmentService.updateAppointment("appointmentId", new Appointment(), "patientId");
        assertTrue(success);
    }

    @Test
    public void testDeleteAppointment() {
        when(appointmentRepository.findById("appointmentId")).thenReturn(Optional.of(testAppointment));

        // Mocking the behavior of MongoTemplate for delete
        ExecutableUpdate<Patient> executableUpdate = mock(ExecutableUpdate.class);
        UpdateWithUpdate<Patient> updateWithUpdate = mock(UpdateWithUpdate.class);
        TerminatingUpdate<Patient> terminatingUpdate = mock(TerminatingUpdate.class);

        when(mongoTemplate.update(Patient.class)).thenReturn(executableUpdate);
        when(executableUpdate.matching(any(Criteria.class))).thenReturn(updateWithUpdate);
        when(updateWithUpdate.apply(any(Update.class))).thenReturn(terminatingUpdate);

        boolean success = appointmentService.deleteAppointment("appointmentId", "patientId");
        assertTrue(success);
    }
    @Test
    public void testGetAppointmentsByPatientId() {
        when(mongoTemplate.findById(anyString(), eq(Patient.class))).thenReturn(testPatient);
        testPatient.setAppointments(Collections.singletonList(testAppointment));

        List<Appointment> results = appointmentService.getAppointmentsByPatientId("patientId");
        assertFalse(results.isEmpty());
        assertEquals(testAppointment, results.get(0));
    }

    @Test
    public void testGetAppointmentsByDoctorId() {
        when(appointmentRepository.findByDoctorId("doctorId")).thenReturn(Arrays.asList(testAppointment));

        List<Appointment> results = appointmentService.getAppointmentsByDoctorId("doctorId");
        assertFalse(results.isEmpty());
        assertEquals(testAppointment, results.get(0));
    }
}
