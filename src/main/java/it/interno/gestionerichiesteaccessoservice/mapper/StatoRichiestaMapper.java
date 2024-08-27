package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.StatoRichiestaDto;
import it.interno.gestionerichiesteaccessoservice.entity.StatoRichiesta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StatoRichiestaMapper {

    StatoRichiesta toEntity(StatoRichiestaDto statoRichiestaDto);

    @InheritInverseConfiguration
    StatoRichiestaDto toDto(StatoRichiesta statoRichiesta);

}
