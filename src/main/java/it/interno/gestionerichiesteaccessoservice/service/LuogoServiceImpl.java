package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.LuogoDto;
import it.interno.gestionerichiesteaccessoservice.mapper.LuogoMapper;
import it.interno.gestionerichiesteaccessoservice.repository.LuogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LuogoServiceImpl implements LuogoService{

    @Autowired
    private LuogoMapper luogoMapper;
    @Autowired
    private LuogoRepository luogoRepository;

    @Override
    public List<LuogoDto> getLuogoByDescrizioneLike(String descrizione, String[] inLuogo, LocalDate dataRif) {
        return luogoRepository.getByDescrizioneLike(descrizione, inLuogo, dataRif)
                .stream()
                .map(el -> luogoMapper.toDto(el))
                .toList();
    }
}
