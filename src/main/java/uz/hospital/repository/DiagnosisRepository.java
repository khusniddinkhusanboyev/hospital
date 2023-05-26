package uz.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hospital.entity.Diagnosis;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis , Integer> {
     List<Diagnosis> findByPatient(Integer id);
}
