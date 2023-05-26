package uz.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hospital.entity.Patient;
import uz.hospital.repository.PatientRepository;
import uz.hospital.util.Encrypt;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> patients(){
        return patientRepository.findAll();
    }
    public Optional<Patient> patientUsernameAndPassword(String username,String password){
        return patientRepository.getPatientByUsernameAndPassword(username,password);
    }

    public Optional<Patient> findPatientById(Integer id){
       return patientRepository.findById(id);
    }

    public Patient save(Patient patient){
       return patientRepository.save(
               Patient
                       .builder()
                       .id(patient.getId())
                       .fullname(patient.getFullname())
                       .typeIllness(patient.getTypeIllness())
                       .email(patient.getEmail())
                       .username(new String(Encrypt.encrypt(patient.getUsername())))
                       .password(new String(Encrypt.encrypt(patient.getPassword())))
                       .time(patient.getTime()).build());
    }

    public void remove(Integer id){
        patientRepository.deleteById(id);
    }


}
