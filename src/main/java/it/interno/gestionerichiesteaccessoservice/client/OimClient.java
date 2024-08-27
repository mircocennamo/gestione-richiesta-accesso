package it.interno.gestionerichiesteaccessoservice.client;

import it.interno.gestionerichiesteaccessoservice.dto.oim.UtenteOimDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "oim", path = "/oim")
public interface OimClient {

    @PostMapping("/utente")
    void creazioneUtente(@RequestBody UtenteOimDto input);
}
