package it.interno.gestionerichiesteaccessoservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.gestionerichiesteaccessoservice.serializer.LocalDateDeserializer;
import it.interno.gestionerichiesteaccessoservice.serializer.LocalDateSerializer;
import it.interno.gestionerichiesteaccessoservice.serializer.TimestampDeserializer;
import it.interno.gestionerichiesteaccessoservice.serializer.TimestampSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtenteUfficioDto {
    private String idUtente;
    private String ufficio;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp dataInserimento;
    private String utenteInserimento;
    private String ufficioInserimento;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp dataCancellazione;
    private String utenteCancellazione;
    private String ufficioCancellazione;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate scadenzaUtente;
    private String orarioLunediDa;
    private String orarioLunediA;
    private String orarioMartediDa;
    private String orarioMartediA;
    private String orarioMercolediDa;
    private String orarioMercolediA;
    private String orarioGiovediDa;
    private String orarioGiovediA;
    private String orarioVenerdiDa;
    private String orarioVenerdiA;
    private String orarioSabatoDa;
    private String orarioSabatoA;
    private String orarioDomenicaDa;
    private String orarioDomenicaA;

    private List<ForzaPoliziaDto> forzePoliziaAssegnabili;
}
