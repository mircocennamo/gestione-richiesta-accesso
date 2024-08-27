package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.MotivazioneRifiutoDto;
import it.interno.gestionerichiesteaccessoservice.entity.MotivazioneRifiuto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MotivazioneRifiutoMapper {

    MotivazioneRifiuto toEntity(MotivazioneRifiutoDto motivazioneRifiutoDto);

    @InheritInverseConfiguration
    MotivazioneRifiutoDto toDto(MotivazioneRifiuto motivazioneRifiuto);
}
