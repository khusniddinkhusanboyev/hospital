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
import uz.hospital.util.Decrypting;
import uz.hospital.util.Encrypt;

import java.util.stream.Collectors;

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
                patientService
                        .patients());
        model.addAttribute(
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
        model.addAttribute("model" , new Patient());
        return "register";
    }

    @GetMapping("/add-register")
    public String save(@ModelAttribute("model") Patient patient , Model model){
       patientService.save(patient);
        return "redirect:/register";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Integer id){
        patientService.remove(id);
        return "redirect:/register";
    }

    /**Diagnosis**/
    @GetMapping("/list-diagnosis")
    public String diognosisList(Model model){
        model.addAttribute("new-diagnosis" , new Diagnosis());
        model.addAttribute("diagnosis" , diagnosisService.diagnosisList());
        return "diagnosis";
    }

    @PostMapping("/save-diagnos")
    public String saveDiagnosis(@ModelAttribute("new-diagnosis") Diagnosis diagnosis){
        return "redirect:/list-diagnosis";
    }







}
