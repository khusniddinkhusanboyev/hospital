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


    public static Encrypt encryptPatient(Patient patient){
        RSA rsa=new RSA();
        return Encrypt
                .builder()
                .id(
                        rsa
                                .encryptMessage(patient.getId().toString().getBytes()))
                .fullname(
                        rsa
                                .encryptMessage(patient.getFullname().getBytes()))
                .typeIllness(
                        rsa
                                .encryptMessage(patient.getTypeIllness().getBytes()))
                .username(
                        rsa
                                .encryptMessage(patient.getUsername().getBytes()))
                .password(
                        rsa
                                .encryptMessage(patient.getPassword().getBytes()))
                .email(
                        rsa.
                                encryptMessage(patient.getEmail().getBytes()))
                .time(
                        rsa
                                .encryptMessage(patient.getTime().toString().getBytes()))
                .build();
    }
}
