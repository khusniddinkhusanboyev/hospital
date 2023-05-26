package uz.hospital.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.hospital.entity.Diagnosis;
import uz.hospital.util.KriptaAES;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncryptDiagnosis{
    /**Diagnosis**/
    private byte[] id;
    private byte[] doctor;
    private byte[] diagnosis;
    private byte[] patient;
    private byte[] localDateTime;

    public static EncryptDiagnosis encryptDiagnosis(Diagnosis diagnosis){
        KriptaAES k = new KriptaAES();
        String secret_key = "secret-code-password";
        return EncryptDiagnosis
                .builder()
                .id(k.encrypt(diagnosis.getId().toString(),secret_key))
                .doctor(k.encrypt(diagnosis.getDoctor(),secret_key))
                .diagnosis(k.encrypt(diagnosis.getDiagnosis() , secret_key))
                .patient(k.encrypt(diagnosis.getPatient().toString() , secret_key))
               /* .localDateTime(k.encrypt(diagnosis.getLocalDateTime().toString(),secret_key))*/
                .build();
    }
}