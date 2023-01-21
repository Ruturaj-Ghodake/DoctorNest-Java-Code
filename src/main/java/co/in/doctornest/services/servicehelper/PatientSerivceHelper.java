package co.in.doctornest.services.servicehelper;

import co.in.doctornest.entity.Doctor;
import co.in.doctornest.entity.Patient;
import co.in.doctornest.payloads.DoctorDto;
import co.in.doctornest.payloads.PatientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PatientSerivceHelper {

    @Autowired
    private ModelMapper modelMapper;

    public PatientDto mapToDTO(Patient patient) {
        PatientDto p = modelMapper.map(patient, PatientDto.class);
        if (patient.getDoctorSet() != null &&  !patient.getDoctorSet().isEmpty()) {
            Set<Doctor> doctors = patient.getDoctorSet();

            Set<DoctorDto> doctorDtoSet = new HashSet<>();
            for (Doctor d : doctors) {
                DoctorDto doctorDto  = modelMapper.map(d, DoctorDto.class);
                doctorDtoSet.add(doctorDto);
            }
            p.setDoctorDto(doctorDtoSet);
        }
        return p;
    }

    public Patient mapToEntity(PatientDto patientDto) {
        Patient p = modelMapper.map(patientDto, Patient.class);
        if (patientDto.getDoctorDto() != null) {
            Set<DoctorDto> doctors = patientDto.getDoctorDto();

            Set<Doctor> doctorSet = new HashSet<>();
            for (DoctorDto d : doctors) {
                Doctor doctor = modelMapper.map(d, Doctor.class);
                doctorSet.add(doctor);
            }
            p.setDoctorSet(doctorSet);
        }
        return p;
    }
}
