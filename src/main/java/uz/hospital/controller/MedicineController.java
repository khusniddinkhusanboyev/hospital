package uz.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hospital.entity.Diagnosis;
import uz.hospital.entity.Patient;

import uz.hospital.service.DiagnosisService;
import uz.hospital.service.PatientService;
import uz.hospital.util.DecryptDiagnosis;


@Controller
@RequiredArgsConstructor
public class MedicineController {
private final PatientService patientService;
private final DiagnosisService diagnosisService;


    @GetMapping("/register")
    public String price(

            Model model
    ){
        model.addAttribute(
                "patients" ,
                patientService.patients()
                        .stream()
                        .map(p->
                                p.builder()
                                        .id(p.getId())
                                        .fullname(p.getFullname())
                                        .typeIllness(p.getTypeIllness())
                                        .username(DecryptDiagnosis.decrypt(p.getUsername()))
                                        .password(DecryptDiagnosis.decrypt(p.getPassword()))
                                        .email(p.getEmail())
                                        .time(p.getTime())
                                        .build())
                        .toList()
        );
       /* model.addAttribute(
                "encrypts" ,
                patientService
                        .patients()
                        .stream()
                        .map(Encrypt::encryptPatient)
                        .toList());
        model.addAttribute(
                "decrypts" ,
                patientService
                        .patients()
                        .stream()
                        .map(Encrypt::encryptPatient)
                        .toList()
                        .stream()
                        .map(Decrypting::decryptPatient)
                        .toList());
        */model.addAttribute("model" , new Patient());
        return "register";
    }

    @GetMapping("/add-register")
    public String save(@ModelAttribute("model") Patient patient , Model model){
       patientService.save(patient);
        return "redirect:/register";
    }
    @PostMapping("/remove/{id}")
    public String removeDiagnosis(@PathVariable Integer id){
        patientService.remove(id);
        return "redirect:/register";
    }

    /**Diagnosis**/
    @GetMapping("/list-diagnosis")
    public String diognosisList(Model model){
        model.addAttribute("newdiagnosis" , new Diagnosis());
        model.addAttribute("pts" , patientService.patients() );
        model.addAttribute("diagnosis" ,
                diagnosisService
                        .diagnosisList()
                        .stream()
                        .map(dd->
                                dd
                                .builder()
                                        .doctor(DecryptDiagnosis.decrypt(dd.getDoctor()))
                                        .diagnosis(DecryptDiagnosis.decrypt(dd.getDiagnosis()))
                                        .patient(dd.getPatient())
                                        .localDateTime(dd.getLocalDateTime())
                                        .build()
                        )
                        .toList());

        return "diagnosis";
    }

    @PostMapping("/save-diagnos")
    public String saveDiagnosis(@ModelAttribute("newdiagnosis") Diagnosis diagnosis){
        System.err.println(diagnosis.toString());
        diagnosisService.save(diagnosis);
        return "redirect:/list-diagnosis";
    }
    @PostMapping("/del/{id}")
    public String remove(@PathVariable Integer id){
        diagnosisService.remove(id);
        return "redirect:/list-diagnosis";
    }

    /**show - details**/

    @GetMapping("/show-details/{id}")
    public String show(@PathVariable Integer id, Model model){
        model.addAttribute("showDetails" , patientService.findPatientById(id).get());
        return "show-details";
    }











}
