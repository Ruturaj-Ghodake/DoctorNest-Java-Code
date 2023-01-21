package co.in.doctornest.services;

import co.in.doctornest.payloads.DoctorDto;
import co.in.doctornest.payloads.PatientDto;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {

    DoctorDto createNewDoctor(DoctorDto doctorDto);

    DoctorDto updateDoctor(long id, DoctorDto doctorDto);

    String deleteDoctor(long id);

    DoctorDto getDoctorById(long id);

    List<DoctorDto> getAllDoctors();

    List<PatientDto> getAllPatientsByDoctorId(long id);

}

