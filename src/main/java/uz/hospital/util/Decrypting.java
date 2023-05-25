package uz.hospital.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        RSA rsa = new RSA();
        return Patient
                .builder()
                /*.id(Integer.parseInt(
                        new String(rsa
                                .decryptMessage(encrypt.getId().toString().getBytes()))))
               */ .fullname(
                        new String(rsa
                                .decryptMessage(encrypt.getFullname().toString().getBytes())))
                .typeIllness(new String(rsa
                        .decryptMessage(encrypt.getTypeIllness().toString().getBytes())))
                .username(new String(rsa
                        .decryptMessage(encrypt.getUsername().toString().getBytes())))
                .password(new String(
                        rsa
                                .decryptMessage(encrypt.getPassword().toString().getBytes())))
                .email(new String(
                        rsa.
                                decryptMessage(encrypt.getEmail().toString().getBytes())))
                /*.time(LocalDateTime.parse(new String(rsa
                        .decryptMessage(encrypt.getTime().toString().getBytes())))
                )*/
                .build();
    }


}
