package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.LuogoDto;

import java.time.LocalDate;
import java.util.List;

public interface LuogoService {

    List<LuogoDto> getLuogoByDescrizioneLike(String descrizione, String[] inLuogo, LocalDate dataRif);

}
