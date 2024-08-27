package it.interno.gestionerichiesteaccessoservice.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComandanteUfficiPK implements Serializable {
    private String utenteComandante;
    private String codiceUfficio;
    private Timestamp dataInserimento;
}
