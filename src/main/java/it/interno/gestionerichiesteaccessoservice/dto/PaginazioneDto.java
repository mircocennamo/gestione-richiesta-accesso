package it.interno.gestionerichiesteaccessoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginazioneDto {

    private Integer numeroElementi;
    private Integer pagina;
    private String orderBy;
    private String orderDirection;
}
