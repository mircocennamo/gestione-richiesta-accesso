package it.interno.gestionerichiesteaccessoservice.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtenteUfficioPK implements Serializable {

    private String idUtente;
    private String ufficio;
    private Timestamp dataInserimento;

}
