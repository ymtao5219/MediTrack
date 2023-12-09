package MediTrack.Medi.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalservice;

    @PostMapping("/patients/{patientid}")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalrecord,
            @PathVariable ObjectId patientid) {
        MedicalRecord newMedicalRecord = medicalservice.createMedicalRecord(medicalrecord, patientid);
        return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllAppointment() {
        return new ResponseEntity<List<MedicalRecord>>(medicalservice.getAllMedicalRecord(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MedicalRecord>> getSingleAppointment(@PathVariable ObjectId id) {
        Optional<MedicalRecord> medicalrecord = medicalservice.getSingleMedicalRecord(id);
        if (medicalrecord.isPresent()) {
            return new ResponseEntity<Optional<MedicalRecord>>(medicalrecord,HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PutMapping("/patients/{patientid}/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable ObjectId id, @RequestBody MedicalRecord medicalrecordDetails) {
        MedicalRecord updatedMedicalRecord = medicalservice.updateMedicalRecord(id, medicalrecordDetails);
        return new ResponseEntity<>(updatedMedicalRecord, HttpStatus.OK);
    }
    
}
