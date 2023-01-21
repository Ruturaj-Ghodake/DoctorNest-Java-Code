package co.in.doctornest.repository;

import co.in.doctornest.entity.Doctor;
import co.in.doctornest.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value = "select p.* from patient as p inner join doctor_patient as dp on p.id=dp.patient_id where dp.doctor_id = :id", nativeQuery = true)
    List<Object[]> searchPatients(@Param("id")Long id);
}
