package it.interno.gestionerichiesteaccessoservice.entity;

import it.interno.gestionerichiesteaccessoservice.entity.pk.UtentePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "TSI035_UTENTE", schema = "CCD")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(UtentePK.class)
public class Utente {

    @Id
    @Column(name = "ID_UTE")
    private String idUtente;
    @Id
    @Column(name = "D_REG_INS")
    private Timestamp dataInserimento;

    @Column(name = "N_TELE_UTE")
    private String numeroTelefono;
    @Column(name = "C_FISC")
    private String codiceFiscale;
    @Column(name = "COGN")
    private String cognome;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "SEX")
    private Character sesso;
    @Column(name = "D_NAS")
    private LocalDate dataNascita;
    @Column(name = "C_LUO_NAS")
    private Integer luogoNascita;
    @Column(name = "DES_LUO_NAS")
    private String descrizioneLuogoNascita;
    @Column(name = "SGL_PRV_NAS")
    private String provinciaNascita;
    @Column(name = "D_REG_CAN")
    private Timestamp dataCancellazione;
    @Column(name = "C_GRA")
    private String cGra;
    @Column(name = "C_GRA_RUOLO")
    private String cGraRuolo;
    @Column(name = "ID_T_UTE")
    private Integer idTipoUtente;
    @Column(name = "IND_EMAIL")
    private String email;
    @Column(name = "NOTE_UTENTE")
    private String noteUtente;

}
