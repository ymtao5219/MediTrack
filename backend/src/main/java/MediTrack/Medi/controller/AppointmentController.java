package MediTrack.Medi.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MediTrack.Medi.model.Appointment;
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
    public ResponseEntity<Optional<Appointment>> getSingleAppointment(@PathVariable String id) {
        Optional <Appointment> appointment = appointmentService.getSingleAppointment(id);
        if (appointment.isPresent()) {
            return new ResponseEntity<Optional<Appointment>>(appointment,HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable String id, @RequestBody Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {

        Optional<Appointment> appointment = appointmentService.getSingleAppointment(id);
        if (appointment.isPresent()) {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable String patientId) {
        try {
            ObjectId patientObjectId = new ObjectId(patientId);
            List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientObjectId);

            if (appointments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(appointments, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: implement with Doctor ID 


}
