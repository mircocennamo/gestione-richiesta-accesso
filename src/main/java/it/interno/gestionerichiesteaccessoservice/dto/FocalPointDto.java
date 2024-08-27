package it.interno.gestionerichiesteaccessoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FocalPointDto {
    private String ufficioPartenza;
    private String utente;
    private String codiceQualifica;
    private String ufficio;
    private String email;
}
