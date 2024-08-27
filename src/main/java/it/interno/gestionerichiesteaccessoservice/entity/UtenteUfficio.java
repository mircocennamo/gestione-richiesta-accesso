package it.interno.gestionerichiesteaccessoservice.entity;

import it.interno.gestionerichiesteaccessoservice.entity.pk.UtenteUfficioPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "TSI099_UTE_UFF", schema = "CCD")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(UtenteUfficioPK.class)
public class UtenteUfficio {

    @Id
    @Column(name = "ID_UTE")
    private String idUtente;
    @Id
    @Column(name = "C_UFF_SEGN")
    private String ufficio;
    @Id
    @Column(name = "D_REG_INS")
    private Timestamp dataInserimento;
    @Column(name = "C_UTE_INS")
    private String utenteInserimento;
    @Column(name = "C_UFF_INS")
    private String ufficioInserimento;
    @Column(name = "D_REG_CAN")
    private Timestamp dataCancellazione;
    @Column(name = "C_UTE_CAN")
    private String utenteCancellazione;
    @Column(name = "C_UFF_CAN")
    private String ufficioCancellazione;
    @Column(name = "D_SCAD_UTE")
    private LocalDate scadenzaUtente;
    @Column(name = "O_LUN_DA")
    private String orarioLunediDa;
    @Column(name = "O_LUN_A")
    private String orarioLunediA;
    @Column(name = "O_MAR_DA")
    private String orarioMartediDa;
    @Column(name = "O_MAR_A")
    private String orarioMartediA;
    @Column(name = "O_MER_DA")
    private String orarioMercolediDa;
    @Column(name = "O_MER_A")
    private String orarioMercolediA;
    @Column(name = "O_GIO_DA")
    private String orarioGiovediDa;
    @Column(name = "O_GIO_A")
    private String orarioGiovediA;
    @Column(name = "O_VEN_DA")
    private String orarioVenerdiDa;
    @Column(name = "O_VEN_A")
    private String orarioVenerdiA;
    @Column(name = "O_SAB_DA")
    private String orarioSabatoDa;
    @Column(name = "O_SAB_A")
    private String orarioSabatoA;
    @Column(name = "O_DOM_DA")
    private String orarioDomenicaDa;
    @Column(name = "O_DOM_A")
    private String orarioDomenicaA;

}
