package MediTrack.Medi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import MediTrack.Medi.model.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByDoctorId(String doctorId);

}
