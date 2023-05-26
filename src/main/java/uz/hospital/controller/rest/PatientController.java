package uz.hospital.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.hospital.entity.Patient;
import uz.hospital.response_api.ResponseApi;
import uz.hospital.service.PatientService;
import uz.hospital.util.Decrypting;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/patientUserAndPassword")
    public ResponseEntity<?> patienUserAndPassword(@RequestParam("username") String username, @RequestParam("password") String password) {

        var onePatient = patientService.patients().stream().map(p -> p.builder()
                        .id(p.getId())
                        .fullname(p.getFullname())
                        .typeIllness(p.getTypeIllness())
                        .username(Decrypting.decryptString(p.getUsername()))
                        .password(Decrypting.decryptString(p.getPassword()))
                        .email(p.getEmail())
                        .time(p.getTime())
                        .build()).filter(patient -> patient.getPassword().equals(password) && patient.getUsername().equals(username))
                .findAny();

        if (!onePatient.isPresent()) {
            return new ResponseEntity<>(new ResponseApi(false, "Bu shaxs tizimdan mavjud emas", null), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseApi(true, "patients are found", onePatient.get()));

    }

    @GetMapping("/patients")
    public ResponseEntity<?> patients() {
        var listOfPatients = patientService.patients();
        if (listOfPatients.isEmpty()) {
            return new ResponseEntity<>(new ResponseApi(false, "patients has not been saved!", null), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseApi(true, " patients are found ", listOfPatients));
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> patient(@PathVariable Integer id) {
        var optionalPatient = patientService.findPatientById(id);
        if (!optionalPatient.isPresent()) {
            return new ResponseEntity<>(new ResponseApi(false, "patioent not found", null), HttpStatus.NOT_FOUND);
        }
        Patient patient = patientService.findPatientById(id).get();

        return ResponseEntity.ok(new ResponseApi(true, " ", patient));
    }

    @PostMapping("/save-patient")
    public ResponseEntity<?> savePatient(@RequestBody Patient patient) {
        Optional<Patient> optionalPatient = patientService.findPatientById(patient.getId());
        if (optionalPatient.isPresent()) {
            return new ResponseEntity<>(new ResponseApi(false, "Bad request...", null), HttpStatus.BAD_REQUEST);
        } else {
            patientService.save(patient);
            return new ResponseEntity<>(new ResponseApi(true, "patient has been successfully saved...", patient), HttpStatus.OK);
        }
    }
}
