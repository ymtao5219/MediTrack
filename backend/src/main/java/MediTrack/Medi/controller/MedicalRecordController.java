package MediTrack.Medi.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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

import MediTrack.Medi.model.MedicalRecord;
import MediTrack.Medi.service.MedicalRecordService;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patients/{patientid}/medicalrecords")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalservice;

    @PostMapping()
    public ResponseEntity<MedicalRecord> createMedicalRecord(@Valid @RequestBody MedicalRecord medicalrecord,
            @PathVariable String patientid) {
        MedicalRecord newMedicalRecord = medicalservice.createMedicalRecord(medicalrecord, patientid);
        return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllAppointment() {
        return new ResponseEntity<List<MedicalRecord>>(medicalservice.getAllMedicalRecord(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MedicalRecord>> getSingleAppointment(@PathVariable String id) {
        Optional<MedicalRecord> medicalrecord = medicalservice.getSingleMedicalRecord(id);
        if (medicalrecord.isPresent()) {
            return new ResponseEntity<Optional<MedicalRecord>>(medicalrecord,HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String id,@PathVariable String patientid ,@RequestBody MedicalRecord medicalrecordDetails) {
        boolean isUpdated = medicalservice.updateMedicalRecord(id, patientid,medicalrecordDetails);

        if (isUpdated) {
            return ResponseEntity.ok().build(); // 200 Ok
        } else {
            return ResponseEntity.badRequest().build(); // 400 Update Error
        }

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String id,@PathVariable String patientid) {
        boolean isDeleted = medicalservice.deleteMedicalRecord(id,patientid);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
