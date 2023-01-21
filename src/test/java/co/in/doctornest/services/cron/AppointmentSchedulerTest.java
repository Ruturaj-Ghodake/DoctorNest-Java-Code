package co.in.doctornest.services.cron;

import co.in.doctornest.entity.Patient;
import co.in.doctornest.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentSchedulerTest {

    @Autowired
    private PatientRepository patientRepository;


    @Test
    void scheduler() {
        List<Patient> patientList = patientRepository.findByPatientAttendedIsNull();
    }
}