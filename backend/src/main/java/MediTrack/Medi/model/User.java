package MediTrack.Medi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String hashedPassword;
    private String userType; // e.g., "Doctor", "Patient"
    private String referenceId; // References the corresponding doctor's or patient's _id

}
