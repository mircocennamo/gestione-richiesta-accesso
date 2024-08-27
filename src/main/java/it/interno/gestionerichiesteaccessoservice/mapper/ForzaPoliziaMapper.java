package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.ForzaPoliziaDto;
import it.interno.gestionerichiesteaccessoservice.entity.ForzaPolizia;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ForzaPoliziaMapper {

    ForzaPolizia toEntity(ForzaPoliziaDto forzaPoliziaDto);

    @InheritInverseConfiguration
    ForzaPoliziaDto toDto(ForzaPolizia forzaPolizia);
}
