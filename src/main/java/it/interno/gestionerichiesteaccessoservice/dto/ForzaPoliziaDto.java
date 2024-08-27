package it.interno.gestionerichiesteaccessoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ForzaPoliziaDto {

    private Integer idGruppo;
    private Integer idOrdinamento;
    private String nomeAbbreviato;
    private String nome;

}
