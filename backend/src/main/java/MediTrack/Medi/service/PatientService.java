package MediTrack.Medi.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MediTrack.Medi.model.Patient;
import MediTrack.Medi.model.User;
import MediTrack.Medi.repository.PatientRepository;
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserService userService;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();

    }

    public Optional<Patient> getSinglePatient(String id) {
        return patientRepository.findById(id);
    }

    public boolean deletePatient(String id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true; 
        } else {
            return false; 
        }

    }

    
    public Patient updatePatient(String id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setEmailAddress(patientDetails.getEmailAddress());
        patient.setContactNumber(patientDetails.getContactNumber());
        patient.setAddress(patientDetails.getAddress());
        return patientRepository.save(patient);
    }

    public Patient addPatient(Patient patientDetails , String userId) {
        // Find the associated user
        User user = userService.findUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Set the User's ObjectId in the Doctor
        patientDetails.setUserId(user.getId());

        Patient savedPatient = patientRepository.save(patientDetails);

        user.setReferenceId(savedPatient.getId());
        user.setUserType("Patient");
        userService.updateUser(user.getId(), user);

        return savedPatient;
    }

}
