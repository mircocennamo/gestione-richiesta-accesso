package it.interno.gestionerichiesteaccessoservice.client;

import it.interno.gestionerichiesteaccessoservice.dto.PersonaFisicaDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "personefisiche-service", path = "/codice-fiscale", url = "${interno.personefisiche.url}")
public interface PersonaFisicaClient {

    @PostMapping(path = "/verifica")
     ResponseEntity<ResponseDto<Boolean>> verificaCodiceFiscale(@RequestBody PersonaFisicaDto personaFisicaDto);

}
