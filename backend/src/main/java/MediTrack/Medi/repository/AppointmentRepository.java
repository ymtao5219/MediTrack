package MediTrack.Medi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import MediTrack.Medi.model.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
