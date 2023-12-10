package MediTrack.Medi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patients")
@Data
public class Patient {
    @Id
    private String id;
    private String userId; // Reference to User
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    @Pattern(regexp="(^$|\\+?[0-9\\- ]+)")
    private String contactNumber;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String emailAddress;
    private String address;
    private LocalDate registrationDate;
    private List<MedicalRecord> medicalRecords;
    private List<Appointment> appointments;

}
