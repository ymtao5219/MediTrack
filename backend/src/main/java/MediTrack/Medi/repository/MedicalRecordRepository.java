package MediTrack.Medi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import MediTrack.Medi.model.MedicalRecord;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    List<MedicalRecord> findBydoctorId(String DoctorId);
}
