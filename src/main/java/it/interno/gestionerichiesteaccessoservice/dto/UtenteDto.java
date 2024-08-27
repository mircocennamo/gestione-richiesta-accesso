package it.interno.gestionerichiesteaccessoservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.gestionerichiesteaccessoservice.serializer.LocalDateDeserializer;
import it.interno.gestionerichiesteaccessoservice.serializer.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDto {
    private String idUtente;
    private String numeroTelefono;
    private String codiceFiscale;
    private String cognome;
    private String nome;
    private Character sesso;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascita;
    private Integer luogoNascita;
    private String descrizioneLuogoNascita;
    private String provinciaNascita;
    private String cGra;
    private Integer idTipoUtente;
    private String email;
    private String noteUtente;
    private String funzione;
}
