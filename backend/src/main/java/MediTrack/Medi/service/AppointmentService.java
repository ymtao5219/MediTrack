package MediTrack.Medi.service;

import java.util.List;

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

    public Appointment createAppointment(Appointment appointment, ObjectId patientid) {
        Appointment newAppointment = appointmentRepository.insert(appointment);

        mongoTemplate.update(Patient.class).matching(Criteria.where("id").is(patientid))
                .apply(new Update().push("appointments", newAppointment)).first();
        
        return newAppointment;
    }

    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public Appointment getSingleAppointment(ObjectId id) {
        return appointmentRepository.findById(id).get();
    }

    public Appointment updateAppointment(ObjectId id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setDateOfAppointment(appointmentDetails.getDateOfAppointment());
        appointment.setNotes(appointmentDetails.getNotes());
        return appointmentRepository.save(appointment);
    }
}
