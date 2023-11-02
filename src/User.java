public class User {

    private String _id;
    private String username;
    private String hashedPassword;
    private String userType;  // Either "Doctor" or "Patient"
    private String referenceId;
    private String userId;
    private Patient patient;  // Link to associated patient, if userType is "Patient"
    private Doctor doctor;    // Link to associated doctor, if userType is "Doctor"


    // Reference to the associated Doctor.
    // Only set this if the userType is "Doctor".

    public User() {
    }

    public User(String _id, String username, String hashedPassword, String userType, String referenceId) {
        this._id = _id;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.userType = userType;
        this.referenceId = referenceId;
        this.userId = _id;
    }

    // Standard getters and setters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (this.doctor != doctor) {  // To prevent infinite loop
            this.doctor = doctor;
            if (doctor != null && !doctor.getUserId().equals(this._id)) {
                doctor.setUser(this);  // Link the Doctor back to this User
            }
        }
    }

    public String getUserId() {
        return userId;
    }

    public void linkToPatient(Patient patient) {
        if (!this.userType.equals("Patient")) {
            throw new IllegalStateException("This user type cannot be linked to a Patient");
        }
        this.patient = patient;
        patient.setUser(this);  // Link the Patient back to this User
    }

    public void linkToDoctor(Doctor doctor) {
        if (!this.userType.equals("Doctor")) {
            throw new IllegalStateException("This user type cannot be linked to a Doctor");
        }
        this.doctor = doctor;
        doctor.setUser(this);   // Link the Doctor back to this User
    }

    public String getPatientId() {
        if (this.patient != null) {
            return this.patient.getPatientId();
        }
        throw new IllegalStateException("This user is not linked to a Patient");
    }

    public String getDoctorId() {
        if (this.doctor != null) {
            return this.doctor.getDoctorId();
        }
        throw new IllegalStateException("This user is not linked to a Doctor");
    }


    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", userType='" + userType + '\'' +
                ", referenceId='" + referenceId + '\'' +
                '}';
    }
}
