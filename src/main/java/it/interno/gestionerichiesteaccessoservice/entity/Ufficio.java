package it.interno.gestionerichiesteaccessoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "SEC_UFFICIO_LEVEL", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ufficio {

    @Id
    @Column(name = "COD_UFF")
    private String codiceUfficio;
    @Column(name = "UFFICIO")
    private String descrizioneUfficio;
    @Column(name = "FORZA_POLIZIA")
    private Integer forzaPolizia;
    @Column(name = "DATA_INIZIO")
    private LocalDate dataInizio;
    @Column(name = "DATA_FINE")
    private LocalDate dataFine;
    @Column(name = "DATA_CAN")
    private Timestamp dataCancellazione;
}
