package uz.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String doctor;
    private String diagnosis;
    @JoinColumn(name = "patient" , table = "patient" , referencedColumnName = "id")
    private Integer patient;
    private LocalDateTime localDateTime=LocalDateTime.now();
}
