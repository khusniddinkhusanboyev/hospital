package uz.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hospital.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient , Integer> {

}
