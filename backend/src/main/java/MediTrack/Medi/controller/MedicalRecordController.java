package MediTrack.Medi.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MediTrack.Medi.model.MedicalRecord;
import MediTrack.Medi.service.MedicalRecordService;



@RestController
@RequestMapping("/patients/{patientid}/medicalrecords")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalservice;

    @PostMapping()
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalrecord,
            @PathVariable ObjectId patientid) {
        MedicalRecord newMedicalRecord = medicalservice.createMedicalRecord(medicalrecord, patientid);
        return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
    }

}
