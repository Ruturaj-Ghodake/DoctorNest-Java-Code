package co.in.doctornest.repository;

import co.in.doctornest.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByPatientAttendedFalse();

    List<Patient> findByAppointmentIdIsNull();
}
