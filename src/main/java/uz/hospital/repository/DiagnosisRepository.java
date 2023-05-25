package uz.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hospital.entity.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis , Integer> {
}
