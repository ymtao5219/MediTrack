package MediTrack.Medi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MediTrack.Medi.model.Doctor;
import MediTrack.Medi.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get a single doctor by ID
    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(new ObjectId(id));
    }

    // Add a new doctor
    public Doctor addDoctor(Doctor doctorDetails) {
        return doctorRepository.save(doctorDetails);
    }

    // Update a doctor's details
    public Doctor updateDoctor(String id, Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setFirstName(doctorDetails.getFirstName());
        doctor.setLastName(doctorDetails.getLastName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setContactNumber(doctorDetails.getContactNumber());
        doctor.setEmailAddress(doctorDetails.getEmailAddress());

        // Additional fields can be set here as needed

        return doctorRepository.save(doctor);
    }

    // Delete a doctor
    public boolean deleteDoctor(String id) {
        ObjectId objectId = new ObjectId(id);
        if (doctorRepository.existsById(objectId)) {
            doctorRepository.deleteById(objectId);
            return true;
        } else {
            return false;
        }
    }
}
