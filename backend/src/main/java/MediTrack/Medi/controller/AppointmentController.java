package MediTrack.Medi.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MediTrack.Medi.model.Appointment;
import MediTrack.Medi.service.AppointmentService;

@RestController
@RequestMapping("/patients/{patientid}/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment,
            @PathVariable ObjectId patientid) {
        Appointment newAppointment = appointmentService.createAppointment(appointment, patientid);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }
    
}
