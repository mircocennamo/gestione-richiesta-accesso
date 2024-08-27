package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.UsersDto;
import it.interno.gestionerichiesteaccessoservice.entity.Users;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UsersMapper {

    Users toEntity(UsersDto usersDto);

    @InheritInverseConfiguration
    UsersDto toDto(Users users);
}
