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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RichiestaRegistrazioneDto {
    private String idProtocollo;
    private String nome;
    private String cognome;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascita;
    private Character sesso;
    private String luogoNascita;
    private String codiceFiscale;
    private String email;
    private String telefono;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp dataRichiesta;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp dataAggiornamento;
    private String emailPrivata;
    private Integer emailPrimaria;
    private Integer flagDownload;
    private String note;
    private String siglaProvinciaDiNascita;
    private String paeseDiNascita;
    private String comandanteAutodichiarato;
    private String prefissoTelefono;
    private QualificaDto qualifica;
    private UfficioDto ufficio;
    private ForzaPoliziaDto forzaPolizia;
    private MotivazioneRifiutoDto motivazioneRifiuto;
    private StatoRichiestaDto statoRichiesta;
    private Integer codiceLuogoNascita;
}
