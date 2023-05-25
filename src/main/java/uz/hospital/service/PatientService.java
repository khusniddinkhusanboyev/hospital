package uz.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hospital.entity.Patient;
import uz.hospital.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> patients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findPatientById(Integer id){
       return patientRepository.findById(id);
    }

    public Patient save(Patient patient){
       return patientRepository.save(patient);
    }

    public void remove(Integer id){
        patientRepository.deleteById(id);
    }


}
