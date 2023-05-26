package uz.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.hospital.entity.Diagnosis;
import uz.hospital.repository.DiagnosisRepository;
import uz.hospital.util.Encrypt;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    public Diagnosis save(Diagnosis diagnosis) {
        return diagnosisRepository.save(
                Diagnosis
                        .builder()
                        .doctor(new String(Encrypt.encrypt(diagnosis.getDoctor())))
                        .diagnosis(new String(Encrypt.encrypt(diagnosis.getDiagnosis())))
                        .patient(diagnosis.getPatient())
                        .localDateTime(diagnosis.getLocalDateTime())
                        .build()
        );
    }

    public Optional<Diagnosis> findById(Integer id) {
        return diagnosisRepository.findById(id);
    }
    public List<Diagnosis> findByPatientId(Integer id){
        return diagnosisRepository.findByPatient(id);
    }

    public List<Diagnosis> diagnosisList() {
        return diagnosisRepository.findAll();
    }
    public void remove(Integer id){
        diagnosisRepository.deleteById(id);
    }
}
