package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.FunzioneDto;

import java.util.List;

public interface FunzioneService {
    List<FunzioneDto> getAllFunzioni();
    FunzioneDto getFunzioneByMappingQualificaProfilo(String codiceQualificaProfilo);
}
