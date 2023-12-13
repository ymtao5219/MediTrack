package MediTrack.Medi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;

import MediTrack.Medi.model.MedicalRecord;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public MedicalRecord createMedicalRecord(MedicalRecord medicalrecord, String patientid) {
        MedicalRecord newMedicalRecord = medicalRecordRepository.insert(medicalrecord);

        mongoTemplate.update(Patient.class).matching(Criteria.where("id").is(patientid))
                .apply(new Update().push("medicalRecords", newMedicalRecord)).first();
        
        return newMedicalRecord;
    }

    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> getSingleMedicalRecord(String id) {
        return medicalRecordRepository.findById(id);
    }
    public boolean updateMedicalRecord(String id, String patientId,MedicalRecord medicalrecordDetails) {
        MedicalRecord medicalrecord = medicalRecordRepository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Medical Record not found"));

        medicalrecord.setRecordType(medicalrecordDetails.getRecordType());
        medicalrecord.setFileLocation(medicalrecordDetails.getFileLocation());
        medicalrecord.setDateOfSubmission(medicalrecordDetails.getDateOfSubmission());
        medicalrecord.setRecordDescription(medicalrecordDetails.getRecordDescription());
        medicalRecordRepository.save(medicalrecord);

        Query query = new Query(Criteria.where("_id").is(patientId).and("medicalRecords.recordId").is(id));



        // Define the update object to set the new values of the appointment fields
        Update update = new Update()
            .set("medicalRecords.$.recordType", medicalrecord.getRecordType())
            .set("medicalRecords.$.fileLocation", medicalrecord.getFileLocation())
            .set("medicalRecords.$.recordDescription", medicalrecord.getRecordDescription())
            .set("medicalRecords.$.dateOfSubmission", medicalrecord.getDateOfSubmission());
        // Perform the update operation
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Patient.class);

        // Check if the update was successful
        return updateResult.getModifiedCount() > 0;

    }
    public boolean deleteMedicalRecord(String id, String patientid) {
        // Check if the medical record exists
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(id);
        if (medicalRecord.isPresent()) {
            // Delete the medical record from the medicalRecord collection
            medicalRecordRepository.deleteById(id);

            // Delete the medical record from the patient's medicalRecords array
            mongoTemplate.update(Patient.class).matching(Criteria.where("id").is(patientid))
                    .apply(new Update().pull("medicalRecords", new BasicDBObject("_id", id))).first();

            return true;
        } else {
            return false;
        }

    }

    
}
