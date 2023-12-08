package MediTrack.Medi.service;

import java.util.List;

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
}
