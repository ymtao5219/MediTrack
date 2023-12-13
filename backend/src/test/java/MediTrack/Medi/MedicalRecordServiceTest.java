package MediTrack.Medi;

import MediTrack.Medi.model.MedicalRecord;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.repository.MedicalRecordRepository;
import MediTrack.Medi.service.MedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation.ExecutableUpdate;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation.TerminatingUpdate;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation.UpdateWithUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    private MedicalRecord testMedicalRecord;
    private Patient testPatient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testMedicalRecord = new MedicalRecord();
        // Initialize testMedicalRecord fields
        testPatient = new Patient();
        // Initialize testPatient fields
    }


    @Test
    public void testCreateMedicalRecord() {
        when(medicalRecordRepository.insert(any(MedicalRecord.class))).thenReturn(testMedicalRecord);

        // Mocking the behavior of MongoTemplate
        ExecutableUpdate<Patient> executableUpdate = mock(ExecutableUpdate.class);
        UpdateWithUpdate<Patient> updateWithUpdate = mock(UpdateWithUpdate.class);
        TerminatingUpdate<Patient> terminatingUpdate = mock(TerminatingUpdate.class);

        when(mongoTemplate.update(Patient.class)).thenReturn(executableUpdate);
        when(executableUpdate.matching(any(Criteria.class))).thenReturn(updateWithUpdate);
        when(updateWithUpdate.apply(any(Update.class))).thenReturn(terminatingUpdate);

        MedicalRecord result = medicalRecordService.createMedicalRecord(testMedicalRecord, "patientId");
        assertNotNull(result);
    }
    @Test
    public void testGetAllMedicalRecord() {
        when(medicalRecordRepository.findAll()).thenReturn(Arrays.asList(testMedicalRecord));
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecord();
        assertFalse(records.isEmpty());
        assertEquals(testMedicalRecord, records.get(0));
    }

    @Test
    public void testGetSingleMedicalRecord() {
        when(medicalRecordRepository.findById(anyString())).thenReturn(Optional.of(testMedicalRecord));
        Optional<MedicalRecord> result = medicalRecordService.getSingleMedicalRecord("recordId");
        assertTrue(result.isPresent());
        assertEquals(testMedicalRecord, result.get());
    }

}