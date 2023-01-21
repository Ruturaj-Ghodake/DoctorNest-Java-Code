package co.in.doctornest.controller;

import co.in.doctornest.payloads.PatientDto;
import co.in.doctornest.repository.PatientRepository;
import co.in.doctornest.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/appointment/doc/{id}")
    public ResponseEntity<PatientDto> savePatientDetails(@PathVariable long id, @Valid @RequestBody PatientDto patientDto){
      PatientDto savedPatient=  patientService.createNewPatient(id, patientDto);
      return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

}
