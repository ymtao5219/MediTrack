package MediTrack.Medi.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import MediTrack.Medi.model.Appointment;
import MediTrack.Medi.model.MedicalRecord;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public MedicalRecord createMedicalRecord(MedicalRecord medicalrecord, ObjectId patientid) {
        MedicalRecord newMedicalRecord = medicalRecordRepository.insert(medicalrecord);

        mongoTemplate.update(Patient.class).matching(Criteria.where("id").is(patientid))
                .apply(new Update().push("medicalRecords", newMedicalRecord)).first();
        
        return newMedicalRecord;
    }

    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> getSingleMedicalRecord(ObjectId id) {
        return medicalRecordRepository.findById(id);
    }
}
