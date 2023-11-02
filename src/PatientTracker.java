import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PatientTracker {

    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<User> users;
    private List<Visit> visits;
    private List<MedicalRecord> medicalRecords;

    public PatientTracker() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.users = new ArrayList<>();
        this.visits = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
    }

    public boolean addPatient(Patient patient) {
        if (isUserIdUsed(patient.getUserId())) {
            System.out.println("This user ID is already in use for a Patient or Doctor.");
            return false;
        }
        patients.add(patient);
        users.add(patient.getUser());
        return true;
    }

    public boolean addDoctor(Doctor doctor) {
        if (isUserIdUsed(doctor.getUserId())) {
            System.out.println("This user ID is already in use for a Patient or Doctor.");
            return false;
        }
        doctors.add(doctor);
        users.add(doctor.getUser());
        return true;
    }

    private boolean isUserIdUsed(String userId) {
        return users.stream().anyMatch(u -> u.getUserId().equals(userId));
    }

    public static void main(String[] args) {
        PatientTracker tracker = new PatientTracker();

        // Sample usage:
        User newUser = new User("exampleUserId1", "username1", "password1", "user@email.com", "USER_ROLE");
        Patient newPatient = new Patient("exampleObjectId1", "John", "Doe", new Date(), "Male", "123-456-7890", "john.doe@email.com", "123 Main St, City, Country", new Date(), new ArrayList<>(), new ArrayList<>(), newUser);
        tracker.addPatient(newPatient);

        User newDoctorUser = new User("exampleUserId2", "username2", "password2", "doctor@email.com", "DOCTOR_ROLE");
        Doctor newDoctor = new Doctor("exampleObjectId5", "Alice", "Smith", "Cardiologist", "234-567-8901", "alice.smith@email.com", newDoctorUser);
        tracker.addDoctor(newDoctor);
    }
}
