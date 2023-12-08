package MediTrack.Medi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.service.PatientService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<List<Patient>>(patientService.getAllPatients(),HttpStatus.OK);

    }

    @GetMapping("/{patientid}")
    public ResponseEntity<Optional<Patient>> getSingle_Patient(@PathVariable String patientid) {
        return new ResponseEntity<Optional<Patient>>(patientService.getSinglePatient(patientid),HttpStatus.OK);
    }
    @DeleteMapping("/{patientid}")
    public ResponseEntity<Void> deletePatient(@PathVariable String patientid) {

        Optional<Patient> patient = patientService.getSinglePatient(patientid);
        if (patient.isPresent()) {
            patientService.deletePatient(patientid);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

    }
    @PutMapping("/{patientid}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String patientid, @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(patientid, patientDetails);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient newPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }


/* 

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable String id, @RequestBody Patient patientDetails) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setFirstName(patientDetails.getFirstName());
                    patient.setLastName(patientDetails.getLastName());
                    // ... other fields
                    return patientRepository.save(patient);
                }).orElseGet(() -> {
                    patientDetails.setPatientId(id);
                    return patientRepository.save(patientDetails);
                });
    }


    */
}

