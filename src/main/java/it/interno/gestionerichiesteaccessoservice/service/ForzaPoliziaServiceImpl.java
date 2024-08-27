package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.ForzaPoliziaDto;
import it.interno.gestionerichiesteaccessoservice.mapper.ForzaPoliziaMapper;
import it.interno.gestionerichiesteaccessoservice.repository.ForzaPoliziaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForzaPoliziaServiceImpl implements ForzaPoliziaService{

    @Autowired
    private ForzaPoliziaRepository forzaPoliziaRepository;
    @Autowired
    private ForzaPoliziaMapper forzaPoliziaMapper;

    @Override
    public List<ForzaPoliziaDto> getAll() {
        return forzaPoliziaRepository.getForzePoliziaConMapping()
                .stream()
                .map(el -> forzaPoliziaMapper.toDto(el))
                .toList();
    }
}
