package it.interno.gestionerichiesteaccessoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "SEC_MOTIVAZIONE_RIFIUTO", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MotivazioneRifiuto {

    @Id
    @Column(name = "ID_MOTIVAZIONE")
    private Integer idMotivazione;
    @Column(name = "MOTIVAZIONE")
    private String motivazione;
}
