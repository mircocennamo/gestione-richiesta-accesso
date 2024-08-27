package it.interno.gestionerichiesteaccessoservice.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersOrarioLavoroPK implements Serializable {
    private String codiceUtente;
    private String codiceUfficio;
    private Timestamp dataInsermento;
}
