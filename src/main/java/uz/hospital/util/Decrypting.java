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
