package uz.hospital.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.hospital.entity.Patient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Encrypt {
    private byte[] id;
    private byte[] fullname;
    private byte[] typeIllness;
    private byte[] username;
    private byte[] password;
    private byte[] email;
    private byte[] time;



    public static Encrypt encryptPatient(Patient patient) {
        KriptaAES k = new KriptaAES();
        String secret_key = "secret-code-password";
        return Encrypt
                .builder()
                .id(k.encrypt(patient.getId().toString(),secret_key))
                .fullname(k.encrypt(patient.getFullname(),secret_key))
                .typeIllness(k.encrypt(patient.getTypeIllness() , secret_key))
                .username(k.encrypt(patient.getUsername() , secret_key))
                .password(k.encrypt(patient.getPassword(),secret_key))
                .email(k.encrypt(patient.getEmail() , secret_key))
                .time(k.encrypt(patient.getTime().toString() , secret_key))
                .build();

    }
}
