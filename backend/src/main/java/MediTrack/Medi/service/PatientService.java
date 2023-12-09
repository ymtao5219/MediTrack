package MediTrack.Medi.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MediTrack.Medi.model.Patient;
import MediTrack.Medi.repository.PatientRepository;
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();

    }

    public Optional<Patient> getSinglePatient(ObjectId id) {
        return patientRepository.findById(id);
    }

    public boolean deletePatient(ObjectId id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true; // Patient found and deleted
        } else {
            return false; // Patient not found
        }

    }



    
    public Patient updatePatient(ObjectId id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Update fields
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setEmailAddress(patientDetails.getEmailAddress());
        patient.setContactNumber(patientDetails.getContactNumber());
        patient.setAddress(patientDetails.getAddress());
        // Leave to update medical records and appointments
        // patient.setMedicalRecords(patientDetails.getMedicalRecords());
        // patient.setAppointments(patientDetails.getAppointments());
        return patientRepository.save(patient);
    }

    public Patient addPatient(Patient patientDetails) {
        return patientRepository.save(patientDetails);
    }

}
