package it.interno.gestionerichiesteaccessoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TLUOGO", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Luogo {

    @Id
    @Column(name = "CODICELUOGO")
    private Integer codiceLuogo;
    @Column(name = "DESCRIZIONELUOGO")
    private String descrizioneLuogo;
    @Column(name = "INLUOGO")
    private String inLuogo;
    @Column(name = "DATAINIZIOVALIDITA")
    private LocalDate dataInizioValidita;
    @Column(name = "DATAFINEVALIDITA")
    private LocalDate dataFineValidita;
    @Column(name = "TSCANCELLAZIONE")
    private LocalDateTime tsCancellazione;
    @Column(name = "CODICEREGIONE")
    private String codiceRegione;
    @Column(name = "CODICEPROVINCIA")
    private String codiceProvincia;
    @Column(name = "SIGLAPROVINCIA")
    private String siglaProvincia;

    public Luogo(Integer codiceLuogo) {
        this.codiceLuogo = codiceLuogo;
    }
}
