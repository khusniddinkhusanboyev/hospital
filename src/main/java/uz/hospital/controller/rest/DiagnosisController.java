package uz.hospital.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.hospital.entity.Diagnosis;
import uz.hospital.response_api.ResponseApi;
import uz.hospital.service.DiagnosisService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @PostMapping("/list-diagnosis")
    public ResponseEntity<?> diagnosisList(){
        List<Diagnosis> diagnosisList=diagnosisService.diagnosisList();
        if (diagnosisList.isEmpty()){
            return new ResponseEntity<>(new ResponseApi(false , "There is no diagnosis..." , diagnosisList) , HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new ResponseApi(true , " " , diagnosisList));

    }

    @PostMapping("/diagnosis/{id}")
    public ResponseEntity<?> diagnosis(@PathVariable Integer id){
        Optional<Diagnosis> diagnosisOptional=diagnosisService.findById(id);
        if(diagnosisOptional.isPresent()){
            return new ResponseEntity<>(new ResponseApi(true , " " , diagnosisOptional.get()) , HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseApi(false," Diagnosis with id is not found..." , null) , HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/save-diagnosis")
    public ResponseEntity<?> saveDiagnosis(@RequestBody Diagnosis diagnosis){
        if (diagnosis!=null){
        return new ResponseEntity<>(new ResponseApi(true , "Diagnosis has been succesfully saved!! " , diagnosisService.save(diagnosis)) , HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseApi(false , "diagnosis must not be `null`..." , null) , HttpStatus.BAD_REQUEST);
    }
}
