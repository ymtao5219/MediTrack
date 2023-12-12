package MediTrack.Medi.service;

import MediTrack.Medi.model.User;
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
    @Autowired
    private UserService userService;

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get a single doctor by ID
    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(new ObjectId(id));
    }

    // Add a new doctor
    public Doctor addDoctor(Doctor doctor, String userId) {
        // Find the associated user
        User user = userService.findUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Set the User's ObjectId in the Doctor
        doctor.setUserId(user.getId());

        // Save the Doctor
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Update the User's referenceId with the Doctor's ID and save the User
        user.setReferenceId(savedDoctor.getId());
        userService.updateUser(user.getId(), user);

        return savedDoctor;
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
