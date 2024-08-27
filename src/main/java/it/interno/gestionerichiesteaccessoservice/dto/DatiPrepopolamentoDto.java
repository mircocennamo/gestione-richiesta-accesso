package it.interno.gestionerichiesteaccessoservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.gestionerichiesteaccessoservice.entity.Users;
import it.interno.gestionerichiesteaccessoservice.entity.Utente;
import it.interno.gestionerichiesteaccessoservice.entity.UtenteUfficio;
import it.interno.gestionerichiesteaccessoservice.serializer.LocalDateDeserializer;
import it.interno.gestionerichiesteaccessoservice.serializer.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatiPrepopolamentoDto {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private Character sesso;
    private Integer codiceLuogoNascita;
    private String luogoNascita;
    private String provinciaNascita;
    private String paeseNascita;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascita;
    private String email;
    private String prefissoTelefono;
    private String telefono;
    private Integer idEnte;
    private String codiceUfficio;
    private String ufficio;
    private String categoria;
    private Integer codiceQualifica;
    private String funzione;
    private OrarioLavoroDto orarioLavoro;

    public DatiPrepopolamentoDto(Users utenteSicurezza){
        this.codiceFiscale = utenteSicurezza.getCodiceFiscale();
        this.nome = utenteSicurezza.getNome();
        this.cognome = utenteSicurezza.getCognome();
        this.sesso = utenteSicurezza.getSesso();
        this.codiceLuogoNascita = utenteSicurezza.getCodiceLuogoNascita() == null ? null : utenteSicurezza.getCodiceLuogoNascita().getCodiceLuogo();
        this.luogoNascita = utenteSicurezza.getCodiceLuogoNascita() == null ? null : utenteSicurezza.getCodiceLuogoNascita().getDescrizioneLuogo();
        this.provinciaNascita = utenteSicurezza.getCodiceLuogoNascita() == null ? null : utenteSicurezza.getCodiceLuogoNascita().getSiglaProvincia();
        this.paeseNascita = utenteSicurezza.getCodiceLuogoNascita() == null ? null : utenteSicurezza.getCodiceLuogoNascita().getCodiceLuogo().toString().charAt(0) == '1' ? utenteSicurezza.getCodiceLuogoNascita().getDescrizioneLuogo() : "ITALIA";
        this.dataNascita = utenteSicurezza.getDataNascita();
        this.email = utenteSicurezza.getEmail();
        this.prefissoTelefono = utenteSicurezza.getPrefissoTelefono();
        this.telefono = utenteSicurezza.getTelefono();
        this.idEnte = utenteSicurezza.getForzaPolizia();
        this.codiceUfficio = utenteSicurezza.getCodiceUfficio();
        this.ufficio = null; // DA CALCOLARE DAL CODICE UFFICIO
        this.categoria = null; // DA CALCOLARE DALLA QUALIFICA
        this.codiceQualifica = utenteSicurezza.getQualifica();
        this.funzione = utenteSicurezza.getIdFunzione();
        this.orarioLavoro = null; // DA RECUPERARE DA DB
    }

    public DatiPrepopolamentoDto(Utente utenteSdi, UtenteUfficio ufficioUtenteSdi){
        this.codiceFiscale = utenteSdi.getCodiceFiscale();
        this.nome = utenteSdi.getNome();
        this.cognome = utenteSdi.getCognome();
        this.sesso = utenteSdi.getSesso();
        this.codiceLuogoNascita = utenteSdi.getLuogoNascita();
        this.luogoNascita = utenteSdi.getDescrizioneLuogoNascita();
        this.provinciaNascita = utenteSdi.getProvinciaNascita();
        this.paeseNascita = utenteSdi.getLuogoNascita() == null ? null : utenteSdi.getLuogoNascita().toString().charAt(0) == '1' ? utenteSdi.getDescrizioneLuogoNascita() : "ITALIA";
        this.dataNascita = utenteSdi.getDataNascita();
        this.email = utenteSdi.getEmail();
        this.prefissoTelefono = null; // NON SI RIESCE A CALCOLARE DA SDI
        this.telefono = utenteSdi.getNumeroTelefono();
        this.idEnte = null; // DA CALCOLARE DALL'UFFICIO
        this.codiceUfficio = ufficioUtenteSdi == null ? null : ufficioUtenteSdi.getUfficio();
        this.ufficio = null; // DA CALCOLARE DALL'UFFICIO
        this.categoria = null; // NON PRESENTE SU SDI
        this.codiceQualifica = null; // NON PRESENTE SU SDI
        this.funzione = null; // DA MAPPARE
        this.orarioLavoro = ufficioUtenteSdi == null ? null : new OrarioLavoroDto(
            ufficioUtenteSdi.getOrarioLunediDa(),
            ufficioUtenteSdi.getOrarioLunediA(),
            ufficioUtenteSdi.getOrarioMartediDa(),
            ufficioUtenteSdi.getOrarioMartediA(),
            ufficioUtenteSdi.getOrarioMercolediDa(),
            ufficioUtenteSdi.getOrarioMercolediA(),
            ufficioUtenteSdi.getOrarioGiovediDa(),
            ufficioUtenteSdi.getOrarioGiovediA(),
            ufficioUtenteSdi.getOrarioVenerdiDa(),
            ufficioUtenteSdi.getOrarioVenerdiA(),
            ufficioUtenteSdi.getOrarioSabatoDa(),
            ufficioUtenteSdi.getOrarioSabatoA(),
            ufficioUtenteSdi.getOrarioDomenicaDa(),
            ufficioUtenteSdi.getOrarioDomenicaA()
        );
    }

}
