package MediTrack.Medi.repository;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import MediTrack.Medi.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, ObjectId>{
}


