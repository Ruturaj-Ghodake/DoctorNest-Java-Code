package co.in.doctornest.services.servicehelper;

import co.in.doctornest.entity.Doctor;
import co.in.doctornest.entity.Patient;
import co.in.doctornest.enums.Gender;
import co.in.doctornest.exceptions.DoctorNestAPIException;
import co.in.doctornest.payloads.DoctorDto;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DoctorServiceHelper {
    public static Doctor updateDetails(Doctor d, DoctorDto doctorDto) {
        if (doctorDto.getFirstName() != null || doctorDto.getLastName() != null) {
            throw new DoctorNestAPIException(HttpStatus.BAD_REQUEST, "First name or Last name can not be changed");
        }
        if (doctorDto.getEmail() != null) {
            throw new DoctorNestAPIException(HttpStatus.BAD_REQUEST, "Email id can not be changed");
        }
        if (doctorDto.getGender() != null) {
            throw new DoctorNestAPIException(HttpStatus.BAD_REQUEST, "Gender can not be changed");
        }
        if (doctorDto.getSpecialist() != null
                || doctorDto.getStateCouncil() != null
                || doctorDto.getRegistrationNo() != null) {
            throw new DoctorNestAPIException(HttpStatus.BAD_REQUEST, "Qualification details can not be changed");
        } else {
            d.setPassword((doctorDto.getPassword() != null) ? doctorDto.getPassword() : d.getPassword());
            d.setMobileNo((doctorDto.getMobileNo() != null) ? doctorDto.getMobileNo() : d.getMobileNo());
            d.setAddress1((doctorDto.getFirstName() != null) ? doctorDto.getFirstName() : d.getFirstName());
            d.setAddress2((doctorDto.getFirstName() != null) ? doctorDto.getFirstName() : d.getFirstName());
            d.setAddress3((doctorDto.getFirstName() != null) ? doctorDto.getFirstName() : d.getFirstName());
            d.setState((doctorDto.getFirstName() != null) ? doctorDto.getFirstName() : d.getFirstName());
            d.setDistrict((doctorDto.getFirstName() != null) ? doctorDto.getFirstName() : d.getFirstName());
            d.setCity((doctorDto.getFirstName() != null) ? doctorDto.getFirstName() : d.getFirstName());
        }
        return d;
    }

    public static List<Patient> getPatientFromObjects(List<Object[]> objectList) {
        List<Patient> patients = new ArrayList<>();

        for (Object[] o : objectList) {
            Patient p = new Patient();
            p.setId((Long) o[0]);
            p.setAddress((String) o[1]);
            p.setAppointmentId((String) o[2]);
            p.setCity((String) o[3]);
            p.setCreatedDate((Date) o[4]);
            p.setDistrict((String) o[5]);
            p.setEmail((String) o[6]);
            p.setFirstName((String) o[7]);
            p.setGender(Gender.findGender((short) o[8]));
            p.setLastName((String) o[9]);
            p.setLasteModifiedDate((Date) o[10]);
            p.setMobileNo((String) o[11]);
            p.setState((String) o[13]);
            p.setPatientAttended((Boolean) o[14]);

            patients.add(p);
        }
        return patients;
    }
}
