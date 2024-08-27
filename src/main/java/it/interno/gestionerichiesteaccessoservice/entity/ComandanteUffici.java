package it.interno.gestionerichiesteaccessoservice.entity;

import it.interno.gestionerichiesteaccessoservice.entity.pk.ComandanteUfficiPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SEC_COMANDANTE_UFFICI", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(ComandanteUfficiPK.class)
public class ComandanteUffici {

    @Id
    @Column(name = "UTE_CMD")
    private String utenteComandante;
    @Id
    @Column(name = "UFF_SEGN")
    private String codiceUfficio;
    @Id
    @Column(name = "DATA_INS")
    private Timestamp dataInserimento;

    @Column(name = "UTE_INS")
    private String utenteInserimento;
    @Column(name = "UFF_INS")
    private String ufficioInserimento;
    @Column(name = "DATA_CAN")
    private Timestamp dataCancellazione;
}
