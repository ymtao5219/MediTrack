package MediTrack.Medi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;

import org.springframework.data.mongodb.core.query.Query;
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

    public boolean updateAppointment(String id, Appointment appointmentDetails, String patientId) {
        Appointment appointment = appointmentRepository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setDateOfAppointment(appointmentDetails.getDateOfAppointment());
        appointment.setNotes(appointmentDetails.getNotes());
        appointmentRepository.save(appointment);
        // Update the appointment in the patient's appointments array
        // Create a query to match the patient and the specific appointment
        Query query = new Query(Criteria.where("_id").is(patientId).and("appointments.appointmentId").is(id));

        // Define the update object to set the new values of the appointment fields
        Update update = new Update()
            .set("appointments.$.dateOfAppointment", appointmentDetails.getDateOfAppointment())
            .set("appointments.$.notes", appointmentDetails.getNotes());
        // Perform the update operation
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Patient.class);

        // Check if the update was successful
        return updateResult.getModifiedCount() > 0;
    }
    public boolean deleteAppointment(String appointmentId, String patientId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);

        if (appointment.isPresent()) {
            // Delete the appointment from the appointment collection
            appointmentRepository.deleteById(appointmentId);

            // Delete the appointment from the patient's appointments array
            mongoTemplate.update(Patient.class).matching(Criteria.where("id").is(patientId))
                    .apply(new Update().pull("appointments", new BasicDBObject("_id", appointmentId))).first();

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
