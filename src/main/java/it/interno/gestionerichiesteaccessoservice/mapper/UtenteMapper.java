package it.interno.gestionerichiesteaccessoservice.mapper;

import it.interno.gestionerichiesteaccessoservice.dto.UtenteDto;
import it.interno.gestionerichiesteaccessoservice.entity.Utente;
import it.interno.gestionerichiesteaccessoservice.repository.FunzioneRepository;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UtenteMapper {

    Utente toEntity(UtenteDto utenteDto);

    @InheritInverseConfiguration
    @Mapping(target = "idUtente", expression = "java(utente.getIdUtente() == null ? null : utente.getIdUtente().trim())")
    @Mapping(target = "numeroTelefono", expression = "java(utente.getNumeroTelefono() == null ? null : utente.getNumeroTelefono().trim())")
    @Mapping(target = "codiceFiscale", expression = "java(utente.getCodiceFiscale() == null ? null : utente.getCodiceFiscale().trim())")
    @Mapping(target = "cognome", expression = "java(utente.getCognome() == null ? null : utente.getCognome().trim())")
    @Mapping(target = "nome", expression = "java(utente.getNome() == null ? null : utente.getNome().trim())")
    @Mapping(target = "descrizioneLuogoNascita", expression = "java(utente.getDescrizioneLuogoNascita() == null ? null : utente.getDescrizioneLuogoNascita().trim())")
    @Mapping(target = "provinciaNascita", expression = "java(utente.getProvinciaNascita() == null ? null : utente.getProvinciaNascita().trim())")
    @Mapping(target = "email", expression = "java(utente.getEmail() == null ? null : utente.getEmail().trim())")
    @Mapping(target = "funzione", expression = "java(utente.getCGra() == null ? null : funzioneRepository.getFunzioneByMappingQualificaProfilo(utente.getCGra()).getIdFunzione())")
    UtenteDto toDto(Utente utente, @Context FunzioneRepository funzioneRepository);
}
