package it.interno.gestionerichiesteaccessoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "SEC_STATO_RICHIESTA", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatoRichiesta {

    @Id
    @Column(name = "ID_STATO")
    private Integer idStato;
    @Column(name = "DSC_STATO")
    private String stato;
}
