package co.in.doctornest.services.impl;

import co.in.doctornest.entity.Doctor;
import co.in.doctornest.entity.Patient;
import co.in.doctornest.exceptions.ResourceNotFoundException;
import co.in.doctornest.payloads.DoctorDto;
import co.in.doctornest.payloads.PatientDto;
import co.in.doctornest.repository.DoctorRepository;
import co.in.doctornest.services.DoctorService;
import co.in.doctornest.services.servicehelper.DoctorServiceHelper;
import co.in.doctornest.services.servicehelper.PatientSerivceHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PatientSerivceHelper helper;

    @Override
    public DoctorDto createNewDoctor(DoctorDto doctorDto) {
        Doctor d = modelMapper.map(doctorDto, Doctor.class);
        Doctor savedDoc = doctorRepository.save(d);

        return modelMapper.map(savedDoc, DoctorDto.class);
    }

    @Override
    public DoctorDto updateDoctor(long id, DoctorDto doctorDto) {
        Doctor d = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Doctor", "Id", id + ""));
        Doctor updatedDetails = DoctorServiceHelper.updateDetails(d, doctorDto);
        updatedDetails.setLasteModifiedDate(new Date());

        return this.modelMapper.map(updatedDetails, DoctorDto.class);
    }


    @Override
    public String deleteDoctor(long id) {
        Doctor d = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Doctor", "Id", id + ""));
        doctorRepository.delete(d);
        return "Doctor Data Deleted";
    }

    @Override
    public DoctorDto getDoctorById(long id) {
        Doctor d = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Doctor", "Id", id + ""));

        return this.modelMapper.map(d, DoctorDto.class);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDto> doctorDtos = new ArrayList<>();
        doctorDtos = doctors.stream().map(d -> modelMapper.map(d, DoctorDto.class)).collect(Collectors.toList());
        return doctorDtos;
    }

    @Override
    public List<PatientDto> getAllPatientsByDoctorId(long id) {
        Doctor d = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Doctor", "Id", id + ""));

        List<Object[]> objects = doctorRepository.searchPatients(id);

        List<PatientDto> patientDtos = new ArrayList<>();

        List<Patient> fetchPatients= DoctorServiceHelper.getPatientFromObjects(objects);
        for (Patient p : fetchPatients) {
            patientDtos.add(helper.mapToDTO(p));
        }
        return patientDtos;
    }


}
