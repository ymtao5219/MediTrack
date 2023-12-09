package MediTrack.Medi.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MediTrack.Medi.model.Appointment;
import MediTrack.Medi.model.Patient;
import MediTrack.Medi.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/patients/{patientid}")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment,
            @PathVariable ObjectId patientid) {
        Appointment newAppointment = appointmentService.createAppointment(appointment, patientid);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        return new ResponseEntity<List<Appointment>>(appointmentService.getAllAppointment(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getSingleAppointment(@PathVariable ObjectId id) {
        return new ResponseEntity<Appointment>(appointmentService.getSingleAppointment(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable ObjectId id, @RequestBody Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    // TODO: implement with Doctor ID 


}
