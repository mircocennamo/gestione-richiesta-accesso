package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.FunzioneDto;
import it.interno.gestionerichiesteaccessoservice.mapper.FunzioneMapper;
import it.interno.gestionerichiesteaccessoservice.repository.FunzioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunzioneServiceImpl implements FunzioneService{

    @Autowired
    private FunzioneRepository funzioneRepository;
    @Autowired
    private FunzioneMapper funzioneMapper;

    @Override
    public List<FunzioneDto> getAllFunzioni() {
        return funzioneRepository.getAllFunzioni().stream()
                .map(el -> funzioneMapper.toDto(el))
                .toList();
    }

    @Override
    public FunzioneDto getFunzioneByMappingQualificaProfilo(String codiceQualificaProfilo) {
        return funzioneMapper.toDto(funzioneRepository.getFunzioneByMappingQualificaProfilo(codiceQualificaProfilo));
    }
}
