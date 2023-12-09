package MediTrack.Medi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import MediTrack.Medi.model.MedicalRecord;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, ObjectId> {
}