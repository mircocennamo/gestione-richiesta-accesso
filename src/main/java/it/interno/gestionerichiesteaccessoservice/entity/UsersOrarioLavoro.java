package it.interno.gestionerichiesteaccessoservice.entity;

import it.interno.gestionerichiesteaccessoservice.dto.OrarioLavoroDto;
import it.interno.gestionerichiesteaccessoservice.entity.pk.UsersOrarioLavoroPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "USERS_ORARIO_LAVORO", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(UsersOrarioLavoroPK.class)
public class UsersOrarioLavoro {

    @Id
    @Column(name = "G_MEMBER")
    private String codiceUtente;
    @Id
    @Column(name = "G_UFFICIO")
    private String codiceUfficio;
    @Id
    @Column(name = "DATE_INS")
    private Timestamp dataInsermento;
    @Column(name = "UTE_INS")
    private String utenteInserimento;
    @Column(name = "UFF_INS")
    private String ufficioInserimento;
    @Column(name = "DATA_CAN")
    private Timestamp datCancellazione;
    @Column(name = "UTE_CAN")
    private String utenteCancellazione;
    @Column(name = "UFF_CAN")
    private String ufficioCancellazione;
    @Column(name = "DATE_SCADENZA_UTENTE")
    private LocalDate scadenzaUtente;
    @Column(name = "O_LUN_DA")
    private String lunediDa;
    @Column(name = "O_LUN_A")
    private String lunediA;
    @Column(name = "O_MAR_DA")
    private String martediDa;
    @Column(name = "O_MAR_A")
    private String martediA;
    @Column(name = "O_MER_DA")
    private String mercolediDa;
    @Column(name = "O_MER_A")
    private String mercolediA;
    @Column(name = "O_GIO_DA")
    private String giovediDa;
    @Column(name = "O_GIO_A")
    private String giovediA;
    @Column(name = "O_VEN_DA")
    private String venerdiDa;
    @Column(name = "O_VEN_A")
    private String venerdiA;
    @Column(name = "O_SAB_DA")
    private String sabatoDa;
    @Column(name = "O_SAB_A")
    private String sabatoA;
    @Column(name = "O_DOM_DA")
    private String domenicaDa;
    @Column(name = "O_DOM_A")
    private String domenicaA;

    public UsersOrarioLavoro(String codiceUtente, String codiceUfficio, Timestamp dataInsermento, String utenteInserimento, String ufficioInserimento, LocalDate scadenzaUtente) {
        this.codiceUtente = codiceUtente;
        this.codiceUfficio = codiceUfficio;
        this.dataInsermento = dataInsermento;
        this.utenteInserimento = utenteInserimento;
        this.ufficioInserimento = ufficioInserimento;
        this.scadenzaUtente = scadenzaUtente;
    }

    public void setOrario (OrarioLavoroDto orarioLacoroDto) {
        this.lunediDa = orarioLacoroDto.getLunediDa();
        this.lunediA = orarioLacoroDto.getLunediA();
        this.martediDa = orarioLacoroDto.getMartediDa();
        this.martediA = orarioLacoroDto.getMartediA();
        this.mercolediDa = orarioLacoroDto.getMercolediDa();
        this.mercolediA = orarioLacoroDto.getMercolediA();
        this.giovediDa = orarioLacoroDto.getGiovediDa();
        this.giovediA = orarioLacoroDto.getGiovediA();
        this.venerdiDa = orarioLacoroDto.getVenerdiDa();
        this.venerdiA = orarioLacoroDto.getVenerdiA();
        this.sabatoDa = orarioLacoroDto.getSabatoDa();
        this.sabatoA = orarioLacoroDto.getSabatoA();
        this.domenicaDa = orarioLacoroDto.getDomenicaDa();
        this.domenicaA = orarioLacoroDto.getDomenicaA();
    }
}
