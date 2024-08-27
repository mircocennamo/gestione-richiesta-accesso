package it.interno.gestionerichiesteaccessoservice.dto.filtro;

import it.interno.gestionerichiesteaccessoservice.dto.PaginazioneDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RicercaRichiestaFiltroDto {

    String nome_cognome;
    String codiceUfficio;
    Integer forzaPolizia;
    PaginazioneDto paginazione;
}
