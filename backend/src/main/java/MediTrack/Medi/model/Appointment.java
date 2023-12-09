package MediTrack.Medi.model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class Appointment {
    @Id
    private String appointmentId;
    private ObjectId doctorId;
    private LocalDate dateOfAppointment;
    private String notes;
}
