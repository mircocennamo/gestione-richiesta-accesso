package it.interno.gestionerichiesteaccessoservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.gestionerichiesteaccessoservice.serializer.TimestampDeserializer;
import it.interno.gestionerichiesteaccessoservice.serializer.TimestampSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileRichiestaDto {
    private Long id;
    private Integer applicationId;
    private String nome;
    private String filename;
    private String mimeType;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp dataCreazione;
    private String blob;
    private RichiestaRegistrazioneDto richiestaRegistrazione;
}
