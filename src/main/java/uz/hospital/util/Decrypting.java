package uz.hospital.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.hospital.entity.Diagnosis;
import uz.hospital.entity.Patient;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Decrypting {
    private Integer id;
    private String fullname;
    private String typeIllness;
    private String username;
    private String password;
    private String email;
    private LocalDateTime time;

    public static Patient decryptPatient(Encrypt encrypt) {
        KriptaAES k = new KriptaAES();
        String secret_key = "secret-code-password";
        return Patient
                .builder()
                .id(Integer.parseInt(new String(k.decrypt(encrypt.getId(), secret_key))))
                .fullname(new String(k.decrypt(encrypt.getFullname(), secret_key)))
                .typeIllness(new String(k.decrypt(encrypt.getTypeIllness(), secret_key)))
                .username(new String(k.decrypt(encrypt.getUsername(), secret_key)))
                .password(new String(k.decrypt(encrypt.getPassword(), secret_key)))
                .email(new String(k.decrypt(encrypt.getEmail(), secret_key)))
                /*.time(LocalDateTime.parse(new String(k.decrypt(encrypt.getTime().toString(), secret_key))))*/
                .build();
    }


}
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class DecryptDiagnosis{
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