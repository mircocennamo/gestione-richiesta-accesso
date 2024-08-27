package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.UfficioDto;
import it.interno.gestionerichiesteaccessoservice.entity.Ufficio;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UfficioMapper {

    Ufficio toEntity(UfficioDto ufficioDto);

    @InheritInverseConfiguration
    UfficioDto toDto(Ufficio ufficio);
}
