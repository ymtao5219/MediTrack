package MediTrack.Medi.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MedicalRecord {
    @Id
    private String recordId;
    private String doctorId;
    private LocalDate dateOfSubmission;
    @NotEmpty
    private String recordType;
    @NotEmpty
    private String recordDescription;
    private String fileLocation;
}
