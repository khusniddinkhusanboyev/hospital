package uz.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hospital.entity.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient , Integer> {

    public Optional<Patient> getPatientByUsernameAndPassword(String username, String password);
}
