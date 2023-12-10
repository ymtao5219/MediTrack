package MediTrack.Medi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import MediTrack.Medi.model.Appointment;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.repository.AppointmentRepository;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Appointment createAppointment(Appointment appointment, String patientid) {
        Appointment newAppointment = appointmentRepository.insert(appointment);

        mongoTemplate.update(Patient.class).matching(Criteria.where("id").is(patientid))
                .apply(new Update().push("appointments", newAppointment)).first();
        
        return newAppointment;
    }

    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getSingleAppointment(String id) {
        return appointmentRepository.findById(id);
    }

    public Appointment updateAppointment(String id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setDateOfAppointment(appointmentDetails.getDateOfAppointment());
        appointment.setNotes(appointmentDetails.getNotes());
        return appointmentRepository.save(appointment);
    }
    public boolean deleteAppointment(String id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true; 
        } else {
            return false; 
        }

    }



    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        Patient patient = mongoTemplate.findById(patientId, Patient.class);
        
        if (patient != null) {
            List<Appointment> appointments = patient.getAppointments();
            // TODO : sort it with OTHER methods 
            // appointments.sort(Comparator.comparing(Appointment::getDateOfAppointment).reversed());
            return appointments;
        } else {
            return Collections.emptyList();
        }
    }
    // Method to get all appointments by doctorId
    public List<Appointment> getAppointmentsByDoctorId(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

}
