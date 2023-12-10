package MediTrack.Medi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import MediTrack.Medi.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String>{
}


