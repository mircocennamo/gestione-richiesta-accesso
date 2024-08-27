package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.FileRichiestaDto;
import it.interno.gestionerichiesteaccessoservice.entity.FileRichiesta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FileRichiestaMapper {

    @Mapping(target = "blob", expression = "java(it.interno.gestionerichiesteaccessoservice.utils.ConversionUtils.convertStringToBlob(fileRichiestaDto.getBlob()))")
    FileRichiesta toEntity(FileRichiestaDto fileRichiestaDto);

    @InheritInverseConfiguration
    @Mapping(target = "blob", expression = "java(it.interno.gestionerichiesteaccessoservice.utils.ConversionUtils.convertBlobToString(fileRichiesta.getBlob()))")
    FileRichiestaDto toDto(FileRichiesta fileRichiesta);
}
