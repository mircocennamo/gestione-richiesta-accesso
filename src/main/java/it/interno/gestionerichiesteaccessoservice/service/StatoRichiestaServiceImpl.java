package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.StatoRichiestaDto;
import it.interno.gestionerichiesteaccessoservice.mapper.StatoRichiestaMapper;
import it.interno.gestionerichiesteaccessoservice.repository.StatoRichiestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatoRichiestaServiceImpl implements StatoRichiestaService{

    @Autowired
    private StatoRichiestaMapper statoRichiestaMapper;
    @Autowired
    private StatoRichiestaRepository statoRichiestaRepository;

    @Override
    public List<StatoRichiestaDto> getAll() {
        return statoRichiestaRepository.findAll()
                .stream()
                .map(el -> statoRichiestaMapper.toDto(el))
                .toList();
    }
}
