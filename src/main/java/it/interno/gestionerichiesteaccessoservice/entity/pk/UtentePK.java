package it.interno.gestionerichiesteaccessoservice.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtentePK implements Serializable {
    private String idUtente;
    private Timestamp dataInserimento;
}
