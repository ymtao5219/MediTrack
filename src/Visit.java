public class Visit {
    private String visitId;
    private Patient patient;
    private Doctor doctor;

    public Visit(String visitId) {
        this.visitId = visitId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        if (this.patient != patient) {
            if (this.patient != null) {
                this.patient.removeVisit(this);
            }
            this.patient = patient;
            if (patient != null) {
                patient.addVisit(this);
            }
        }
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (this.doctor != doctor) {
            if (this.doctor != null) {
                this.doctor.removeVisit(this);
            }
            this.doctor = doctor;
            if (doctor != null) {
                doctor.addVisit(this);
            }
        }
    }

    //其他getters和setters
}
