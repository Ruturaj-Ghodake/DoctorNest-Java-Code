package co.in.doctornest.controller;

import co.in.doctornest.payloads.DoctorDto;
import co.in.doctornest.payloads.PatientDto;
import co.in.doctornest.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doc")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/save")
    public ResponseEntity<DoctorDto> saveDoctorDetails(@Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto savedDoctorDto = doctorService.createNewDoctor(doctorDto);

        return new ResponseEntity<>(savedDoctorDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> fetchDoctorDetailsById(@PathVariable long id){
       return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @GetMapping
    public List<DoctorDto> fetchAllDoctorDetails(){
        return doctorService.getAllDoctors();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctorDetails(@PathVariable long id,
                                                         @RequestBody DoctorDto doctorDto){
      DoctorDto updatedDoctor= doctorService.updateDoctor(id, doctorDto);

      return new ResponseEntity<>(updatedDoctor, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/patients")
    public List<PatientDto> fetchPatientDetails(@PathVariable long id) {
        List<PatientDto> patientDtos = doctorService.getAllPatientsByDoctorId(id);
        return patientDtos;
    }

}
