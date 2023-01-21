package co.in.doctornest.services.impl;

import co.in.doctornest.entity.Doctor;
import co.in.doctornest.entity.Patient;
import co.in.doctornest.exceptions.ResourceNotFoundException;
import co.in.doctornest.payloads.PatientDto;
import co.in.doctornest.repository.DoctorRepository;
import co.in.doctornest.repository.PatientRepository;
import co.in.doctornest.services.PatientService;
import co.in.doctornest.services.servicehelper.PatientSerivceHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientSerivceHelper helper;

    @Override
    public PatientDto createNewPatient(long id, PatientDto patientDto) {
        Set<Doctor> doctorSet = new HashSet<>();
        Doctor d = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Doctor", "Id", id + ""));
        doctorSet.add(d);

        Patient p = modelMapper.map(patientDto, Patient.class);
        p.setDoctorSet(doctorSet);
        p.setPatientAttended(false);
        Patient savedPatientDetails = patientRepository.save(p);

        return  helper.mapToDTO(savedPatientDetails);
    }
}
