package co.in.doctornest.services;

import co.in.doctornest.payloads.PatientDto;

public interface PatientService {

    PatientDto createNewPatient(long id,PatientDto patientDto);
}
