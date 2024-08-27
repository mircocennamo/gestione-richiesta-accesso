package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.QualificaDto;

import java.util.List;

public interface QualificaService {
    List<QualificaDto> getQualificaByCategoriaAndEnte(String categoria, Integer idEnte);
    List<String> getCategoriaQualificaByEnte(Integer idEnte);
    List<String> getRuoloQualifica();
}
