package it.interno.gestionerichiesteaccessoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersDto {
    private String codiceUtente;
    private String codiceUfficio;
    private Integer forzaPolizia;
    private String codiceRegione;
    private String codiceProvincia;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private Character sesso;
    private String luogoNascita;
    private String codiceFiscale;
    private String email;
    private String telefono;
    private String emailPrivata;
    private String utenteInserimento;
    private String ufficioInserimento;
    private Timestamp dataInserimento;
    private Integer emailPrimaria;
    private String siglaProvinciaNacita;
    private Integer idTipoUtente;
    private String paeseDiNascita;
    private String prefissoTelefono;
    private Integer qualifica;
    private String noteUtente;
    private LuogoDto codiceLuogoNascita;
}
