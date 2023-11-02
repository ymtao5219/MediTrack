import java.util.Date;

public class MedicalRecord {

    private String recordId;
    private Date dateOfSubmission;
    private String recordType;
    private String recordDescription;
    private String fileLocation;

    private Patient patient;
    private Doctor doctor;

    public MedicalRecord() {
    }

    public MedicalRecord(String recordId, Date dateOfSubmission, String recordType, 
                         String recordDescription, String fileLocation) {
        this.recordId = recordId;
        this.dateOfSubmission = dateOfSubmission;
        this.recordType = recordType;
        this.recordDescription = recordDescription;
        this.fileLocation = fileLocation;
    }

    // Getters and setters for the basic attributes...

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Date getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(Date dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordDescription() {
        return recordDescription;
    }

    public void setRecordDescription(String recordDescription) {
        this.recordDescription = recordDescription;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        if (this.patient != patient) {
            if (this.patient != null) {
                this.patient.removeMedicalRecord(this);
            }
            this.patient = patient;
            if (patient != null) {
                patient.addMedicalRecord(this);
            }
        }
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (this.doctor != doctor) {
            if (this.doctor != null) {
                this.doctor.removeMedicalRecord(this);
            }
            this.doctor = doctor;
            if (doctor != null) {
                doctor.addMedicalRecord(this);
            }
        }
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", dateOfSubmission=" + dateOfSubmission +
                ", recordType='" + recordType + '\'' +
                ", recordDescription='" + recordDescription + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                '}';
    }
}
