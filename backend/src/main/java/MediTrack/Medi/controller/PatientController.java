package MediTrack.Medi.controller;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.service.PatientService;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<List<Patient>>(patientService.getAllPatients(),HttpStatus.OK);

    }
/* 

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable String id) {
        return patientRepository.findById(id).orElse(null);
    }

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

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientRepository.deleteById(id);
    }
    */
}

