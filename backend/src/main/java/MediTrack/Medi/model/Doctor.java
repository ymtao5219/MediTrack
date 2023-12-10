package MediTrack.Medi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "doctors")
@Data
public class Doctor {
    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String contactNumber;
    private String emailAddress;

}
