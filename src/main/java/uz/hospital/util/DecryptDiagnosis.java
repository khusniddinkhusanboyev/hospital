package uz.hospital.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.hospital.entity.Diagnosis;
import uz.hospital.util.EncryptDiagnosis;
import uz.hospital.util.KriptaAES;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecryptDiagnosis{
    /**Diagnosis**/
    private Integer id;
    private String doctor;
    private String diagnosis;
    private Integer patient;
    private LocalDateTime localDateTime;

    public static Diagnosis decryptPatient(EncryptDiagnosis encryptDiagnosis) {
        KriptaAES k = new KriptaAES();
        String secret_key = "secret-code-password";
        return Diagnosis
                .builder()
                .id(Integer.parseInt(new String(k.decrypt(encryptDiagnosis.getId(), secret_key))))
                .doctor(new String(k.decrypt(encryptDiagnosis.getDoctor(), secret_key)))
                .diagnosis(new String(k.decrypt(encryptDiagnosis.getDiagnosis(), secret_key)))
                .patient(Integer.parseInt(new String(k.decrypt(encryptDiagnosis.getPatient(), secret_key))))
                .localDateTime(LocalDateTime.parse(new String(k.decrypt(encryptDiagnosis.getLocalDateTime(), secret_key))))
                .build();
    }
}