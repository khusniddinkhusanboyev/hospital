package uz.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hospital.entity.Patient;
import uz.hospital.service.PatientService;
import uz.hospital.util.Decrypting;
import uz.hospital.util.Encrypt;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MedicineController {
private final PatientService patientService;

    /*@GetMapping("/index")
    public String index(Model model){
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }
    @GetMapping("/appointment")
    public String appointment(Model model){
        return "appointment";
    }
    @GetMapping("/blog")
    public String blog(Model model){
        return "blog";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        return "contact";
    }
    @GetMapping("/detail")
    public String detail(Model model){
        return "detail";
    }

    @GetMapping("/search")
    public String search(Model model){
        return "search";
    }
    @GetMapping("/service")
    public String service(Model model){
        return "service";
    }
    @GetMapping("/team")
    public String team(Model model){
        return "team";
    }
    @GetMapping("/testimonial")
    public String testimonial(Model model){
        return "testimonial";
    }*/
    @GetMapping("/register")
    public String price(
          /*  @RequestParam("typeIllness") String typeIllness,
            @RequestParam("username") String username,
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,*/
            Model model
    ){
        model.addAttribute("patients" , patientService.patients());
        model.addAttribute("encrypts" , patientService.patients().stream().map(Encrypt::encryptPatient).collect(Collectors.toList()) );
        model.addAttribute("decrypts" , patientService.patients().stream().map(Encrypt::encryptPatient).toList().stream().map(Decrypting::decryptPatient).toList());
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






}
