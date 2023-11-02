import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
    private String patientId;
    private String name;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();
    private User user;  // User associated with this patient


    public Patient(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    public Patient(String exampleObjectId1, String john, String doe, Date date, String male, String s, String s1, String s2, Date date1, ArrayList<Object> objects, ArrayList<Object> objects1, User newUser) {
    }

    public void addMedicalRecord(MedicalRecord record) {
        if (!medicalRecords.contains(record)) {
            medicalRecords.add(record);
            record.setPatient(this);
        }
    }

    public void removeMedicalRecord(MedicalRecord record) {
        if (medicalRecords.contains(record)) {
            medicalRecords.remove(record);
            record.setPatient(null);
        }
    }

    public void addVisit(Visit visit) {
        if (!visits.contains(visit)) {
            visits.add(visit);
            visit.setPatient(this);
        }
    }

    public void removeVisit(Visit visit) {
        if (visits.contains(visit)) {
            visits.remove(visit);
            visit.setPatient(null);
        }
    }


    public User getUser() {
        return user;
    }
    public String getPatientId() {
        return this.patientId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // In Patient class
    public String getUserId() {
        if (user == null) {
            return null; // or you can throw an exception or return a default value
        }
        return user.getUserId();
    }




}
