package it.interno.gestionerichiesteaccessoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrarioLavoroDto {

    private String lunediDa;
    private String lunediA;
    private String martediDa;
    private String martediA;
    private String mercolediDa;
    private String mercolediA;
    private String giovediDa;
    private String giovediA;
    private String venerdiDa;
    private String venerdiA;
    private String sabatoDa;
    private String sabatoA;
    private String domenicaDa;
    private String domenicaA;

}
