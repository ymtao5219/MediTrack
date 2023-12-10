package MediTrack.Medi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.service.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/patients")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<List<Patient>>(patientService.getAllPatients(),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getSingle_Patient(@PathVariable String id) {
        Optional <Patient> patient = patientService.getSinglePatient(id);
        if (patient.isPresent()) {
            return new ResponseEntity<Optional<Patient>>(patient,HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        
        // return new ResponseEntity<Optional<Patient>>(patientService.getSinglePatient(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {

        Optional<Patient> patient = patientService.getSinglePatient(id);
        if (patient.isPresent()) {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@Valid @RequestBody Patient patientDetails,@RequestParam String userId) {
        patientDetails.setUserId(userId);
        Patient newPatient = patientService.addPatient(patientDetails,userId);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }
}

