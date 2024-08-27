package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.MotivazioneRifiutoDto;
import it.interno.gestionerichiesteaccessoservice.mapper.MotivazioneRifiutoMapper;
import it.interno.gestionerichiesteaccessoservice.repository.MotivazioneRifiutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotivazioneRifiutoServiceImpl implements MotivazioneRifiutoService{

    @Autowired
    private MotivazioneRifiutoMapper motivazioneRifiutoMapper;
    @Autowired
    private MotivazioneRifiutoRepository motivazioneRifiutoRepository;

    @Override
    public List<MotivazioneRifiutoDto> getAll() {
        return motivazioneRifiutoRepository.findAll()
                .stream()
                .map(el -> motivazioneRifiutoMapper.toDto(el))
                .toList();
    }
}
