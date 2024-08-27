package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.QualificaDto;
import it.interno.gestionerichiesteaccessoservice.mapper.QualificaMapper;
import it.interno.gestionerichiesteaccessoservice.repository.QualificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificaServiceImpl implements QualificaService{

    @Autowired
    private QualificaMapper qualificaMapper;
    @Autowired
    private QualificaRepository qualificaRepository;

    @Override
    public List<QualificaDto> getQualificaByCategoriaAndEnte(String categoria, Integer idEnte) {
        return qualificaRepository.getQualificaByCategoriaAndEnte(categoria, idEnte)
                .stream()
                .map(el -> qualificaMapper.toDto(el))
                .toList();
    }

    @Override
    public List<String> getCategoriaQualificaByEnte(Integer idEnte) {
        return qualificaRepository.getCategoriaQualificaByEnte(idEnte);
    }

    @Override
    public List<String> getRuoloQualifica() {
        return qualificaRepository.getRuoloQualifica();
    }
}
