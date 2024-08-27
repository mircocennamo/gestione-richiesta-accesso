package it.interno.gestionerichiesteaccessoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "SEC_RICHIESTA_REGISTRAZIONE", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RichiestaRegistrazione {

    @Id
    @Column(name = "IDPROT")
    private String idProtocollo;
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
    @Column(name = "DATA_RICHIESTA")
    private Timestamp dataRichiesta;
    @Column(name = "DATA_AGGIORNAMENTO")
    private Timestamp dataAggiornamento;
    @Column(name = "EMAIL_PRIV")
    private String emailPrivata;
    @Column(name = "PRIMARY_EMAIL")
    private Integer emailPrimaria;
    @Column(name = "FLG_DOWNLOAD")
    private Integer flagDownload;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "SIGLAPROVINCIADINASCITA")
    private String siglaProvinciaDiNascita;
    @Column(name = "PAESEDINASCITA")
    private String paeseDiNascita;
    @Column(name = "COMANDANTEAUTODICHIARATO")
    private String comandanteAutodichiarato;
    @Column(name = "TELEFONO_PRE")
    private String prefissoTelefono;

    @ManyToOne
    @JoinColumn(name = "QUALIFICA", referencedColumnName = "QUALIFICA_ID")
    private Qualifica qualifica;

    @ManyToOne
    @JoinColumn(name = "UFFICIO", referencedColumnName = "COD_UFF")
    private Ufficio ufficio;

    @ManyToOne
    @JoinColumn(name = "FORZA_POLIZIA", referencedColumnName = "GROUP_ID")
    private ForzaPolizia forzaPolizia;

    @ManyToOne
    @JoinColumn(name = "MOT_RIFIUTO", referencedColumnName = "ID_MOTIVAZIONE")
    private MotivazioneRifiuto motivazioneRifiuto;

    @ManyToOne
    @JoinColumn(name = "STATO", referencedColumnName = "ID_STATO")
    private StatoRichiesta statoRichiesta;

}
