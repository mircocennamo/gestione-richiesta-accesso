package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.LuogoDto;
import it.interno.gestionerichiesteaccessoservice.entity.Luogo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LuogoMapper {

    Luogo toEntity(LuogoDto luogoDto);

    @InheritInverseConfiguration
    @Mapping(target = "descrizioneLuogo", expression = "java(luogo.getDescrizioneLuogo() == null ? null : luogo.getDescrizioneLuogo().trim())")
    LuogoDto toDto(Luogo luogo);
}
