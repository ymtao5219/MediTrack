package MediTrack.Medi.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Patient> getSinglePatient(String patientid) {
        return patientRepository.findBypatientid(patientid);
    }

    public Optional<Patient> deletePatient(String patientid) {
        return patientRepository.deleteBypatientid(patientid);
    }



    
    public Patient updatePatient(String patientid, Patient patientDetails) {
        Patient patient = patientRepository.findBypatientid(patientid)
                         .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Update fields
        patient.setFirst_name(patientDetails.getFirst_name());
        patient.setLast_name(patientDetails.getLast_name());
        patient.setDate_of_birth(patientDetails.getDate_of_birth());
        patient.setGender(patientDetails.getGender());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone(patientDetails.getPhone());
        patient.setAddress(patientDetails.getAddress());
        // Add other fields as necessary

        return patientRepository.save(patient);
    }


}
