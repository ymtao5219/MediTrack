package MediTrack.Medi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import org.bson.types.ObjectId;
@Document(collection = "patients")
@Data
public class Patient {
    @Id
    private ObjectId id;
    private Integer patient_id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String registration_date;
    
}
