
package MediTrack.Medi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MediTrack.Medi.model.Appointment;
import MediTrack.Medi.model.MedicalRecord;
import MediTrack.Medi.service.MedicalRecordService;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalservice;

    @PostMapping("/patients/{patientid}")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@Valid @RequestBody MedicalRecord medicalrecord,
            @PathVariable String patientid) {
        MedicalRecord newMedicalRecord = medicalservice.createMedicalRecord(medicalrecord, patientid);
        return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
    }

    @GetMapping("/patients/{patientid}")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByPatientId(@PathVariable String patientid) {
        try {
            List<MedicalRecord> medicalRecords = medicalservice.getMedicalRecordsByPatientId(patientid);

            if (medicalRecords.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/doctors/{DoctorId}")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByDoctorId(@PathVariable String DoctorId) {
        try {
            List<MedicalRecord> medicalRecords = medicalservice.getMedicalRecordsByDoctorId(DoctorId);

            if (medicalRecords.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/patients/{patientid}/{id}")
    public ResponseEntity<Optional<MedicalRecord>> getSingleAppointment(@PathVariable String id) {
        Optional<MedicalRecord> medicalrecord = medicalservice.getSingleMedicalRecord(id);
        if (medicalrecord.isPresent()) {
            return new ResponseEntity<Optional<MedicalRecord>>(medicalrecord,HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PutMapping("/patients/{patientid}/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String id,@PathVariable String patientid ,@RequestBody MedicalRecord medicalrecordDetails) {
        boolean isUpdated = medicalservice.updateMedicalRecord(id, patientid,medicalrecordDetails);

        if (isUpdated) {
            return ResponseEntity.ok().build(); // 200 Ok
        } else {
            return ResponseEntity.badRequest().build(); // 400 Update Error
        }

    }
    
    @DeleteMapping("/patients/{patientid}/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String id,@PathVariable String patientid) {
        boolean isDeleted = medicalservice.deleteMedicalRecord(id,patientid);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
