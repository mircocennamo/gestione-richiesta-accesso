package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.dto.StatoRichiestaDto;
import it.interno.gestionerichiesteaccessoservice.service.StatoRichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stato-richiesta", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Stato Richiesta")
public class StatoRichiestaController {

    @Autowired
    private StatoRichiestaService statoRichiestaService;

    @Operation(summary = "API per recuperare tutti gli stati della richiesta")
    @GetMapping
    public ResponseEntity<ResponseDto<List<StatoRichiestaDto>>> getAllStatiRichiesta(){

        List<StatoRichiestaDto> result = statoRichiestaService.getAll();

        return ResponseEntity.ok(ResponseDto.<List<StatoRichiestaDto>>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }
}
