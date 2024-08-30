package it.interno.gestionerichiesteaccessoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "USERS", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {

    @Id
    @Column(name = "G_MEMBER")
    private String codiceUtente;
    @Column(name = "G_UFFICIO")
    private String codiceUfficio;
    @Column(name = "FORZA_POLIZIA")
    private Integer forzaPolizia;
    @Column(name = "COD_REG")
    private String codiceRegione;
    @Column(name = "COD_PRV")
    private String codiceProvincia;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "COGNOME")
    private String cognome;
    @Column(name = "DATADINASCITA")
    private LocalDate dataNascita;
    @Column(name = "SESSO")
    private Character sesso;
    @Column(name = "LUOGODINASCITA")
    private String luogoNascita;
    @Column(name = "CF")
    private String codiceFiscale;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "EMAIL_PRIV")
    private String emailPrivata;
    @Column(name = "UTE_INS")
    private String utenteInserimento;
    @Column(name = "UFF_INS")
    private String ufficioInserimento;
    @Column(name = "DATE_INS")
    private Timestamp dataInserimento;
    @Column(name = "UTE_AGG")
    private String utenteAggiornamento;
    @Column(name = "DATA_AGG")
    private Timestamp dataAggiornamento;
    @Column(name = "UTE_CAN")
    private String utenteCancellazione;
    @Column(name = "UFF_CAN")
    private String ufficioCancellazione;
    @Column(name = "DATA_CAN")
    private Timestamp dataCancellazione;
    @Column(name = "PRIMARY_EMAIL")
    private Integer emailPrimaria;
    @Column(name = "SIGLAPROVINCIADINASCITA")
    private String siglaProvinciaNacita;
    @Column(name = "IDTIPOUTENTE")
    private Integer idTipoUtente;
    @Column(name = "PAESEDINASCITA")
    private String paeseDiNascita;
    @Column(name = "TELEFONO_PRE")
    private String prefissoTelefono;
    @Column(name = "QUALIFICA")
    private Integer qualifica;
    @Column(name = "ID_FUNZIONE")
    private String idFunzione;
    @Column(name = "NOTEUTENTE")
    private String noteUtente;

   //fix bug numero 98
    @Column(name = "REGIONE")
    private String regione;
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "RIPARTIZIONE")
    private String ripartizione;


    @ManyToOne
    @JoinColumn(name = "CODICELUOGODINASCITA", referencedColumnName = "CODICELUOGO")
    private Luogo codiceLuogoNascita;

    public Users(String codiceUtente, String codiceUfficio, Integer forzaPolizia, String codiceRegione, String codiceProvincia, String nome, String cognome, LocalDate dataNascita, Character sesso, String luogoNascita, String codiceFiscale, String email, String telefono, String prefissoTelefono, String emailPrivata, String utenteInserimento, String ufficioInserimento, Timestamp dataInserimento, Integer emailPrimaria, String siglaProvinciaNacita, Integer idTipoUtente, String paeseDiNascita, Integer idQualifica, String idFunzione, Luogo codiceLuogoNascita, String noteUtente
    ,String regione ,String provincia ,String ripartizione ) {
        this.codiceUtente = codiceUtente;
        this.codiceUfficio = codiceUfficio;
        this.forzaPolizia = forzaPolizia;
        this.codiceRegione = codiceRegione;
        this.codiceProvincia = codiceProvincia;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
        this.luogoNascita = luogoNascita;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.telefono = telefono;
        this.emailPrivata = emailPrivata;
        this.utenteInserimento = utenteInserimento;
        this.ufficioInserimento = ufficioInserimento;
        this.dataInserimento = dataInserimento;
        this.emailPrimaria = emailPrimaria;
        this.siglaProvinciaNacita = siglaProvinciaNacita;
        this.idTipoUtente = idTipoUtente;
        this.paeseDiNascita = paeseDiNascita;
        this.qualifica = idQualifica;
        this.idFunzione = idFunzione;
        this.codiceLuogoNascita = codiceLuogoNascita;
        this.noteUtente = noteUtente;
        this.prefissoTelefono = prefissoTelefono;
        this.regione = regione;
        this.provincia = provincia;
        this.ripartizione = ripartizione;
    }
}
