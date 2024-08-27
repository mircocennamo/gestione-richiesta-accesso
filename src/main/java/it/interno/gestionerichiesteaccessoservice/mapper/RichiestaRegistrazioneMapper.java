package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.RichiestaRegistrazioneDto;
import it.interno.gestionerichiesteaccessoservice.entity.RichiestaRegistrazione;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RichiestaRegistrazioneMapper {

    RichiestaRegistrazione toEntity(RichiestaRegistrazioneDto richiestaRegistrazioneDto);

    @InheritInverseConfiguration
    RichiestaRegistrazioneDto toDto(RichiestaRegistrazione richiestaRegistrazione);
}
