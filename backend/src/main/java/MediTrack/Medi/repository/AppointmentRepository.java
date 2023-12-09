package MediTrack.Medi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import MediTrack.Medi.model.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {
}
