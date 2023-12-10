package MediTrack.Medi.repository;

import MediTrack.Medi.model.Doctor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, ObjectId> {
}