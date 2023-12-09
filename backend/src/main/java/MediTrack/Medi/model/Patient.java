package MediTrack.Medi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
@Document(collection = "patients")
@Data
public class Patient {
    @Id
    private ObjectId id;
    private ObjectId userId; // Reference to User
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String address;
    private LocalDate registrationDate;
    private List<MedicalRecord> medicalRecords;
    private List<Appointment> appointments;

}
