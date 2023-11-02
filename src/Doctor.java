import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String doctorId;
    private String name;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();
    private User user;

    public Doctor(String doctorId, String name) {
        this.doctorId = doctorId;
        this.name = name;
    }

    public Doctor(String exampleObjectId5, String alice, String smith, String cardiologist, String s, String s1, User newDoctorUser) {
    }

    public void addMedicalRecord(MedicalRecord record) {
        if (!medicalRecords.contains(record)) {
            medicalRecords.add(record);
            record.setDoctor(this);
        }
    }

    public void removeMedicalRecord(MedicalRecord record) {
        if (medicalRecords.contains(record)) {
            medicalRecords.remove(record);
            record.setDoctor(null);
        }
    }

    public void addVisit(Visit visit) {
        if (!visits.contains(visit)) {
            visits.add(visit);
            visit.setDoctor(this);
        }
    }

    public void removeVisit(Visit visit) {
        if (visits.contains(visit)) {
            visits.remove(visit);
            visit.setDoctor(null);
        }
    }

    public String getDoctorId() {
        return this.doctorId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUserId() {
        if (user == null) {
            return null; // or you can throw an exception or return a default value
        }
        return user.getUserId();

    }
}
