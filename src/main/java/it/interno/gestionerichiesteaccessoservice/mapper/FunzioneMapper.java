package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.FunzioneDto;
import it.interno.gestionerichiesteaccessoservice.entity.Funzione;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FunzioneMapper {

    Funzione toEntity(FunzioneDto funzioneDto);

    @InheritInverseConfiguration
    FunzioneDto toDto(Funzione funzione);
}
