package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.FunzioneDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.service.FunzioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/funzione", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Funzione")
public class FunzioneController {

    @Autowired
    private FunzioneService funzioneService;

    @Operation(summary = "API per recuperare la lista delle funzioni")
    @GetMapping
    public ResponseEntity<ResponseDto<List<FunzioneDto>>> getAllFunzioni(){

        return ResponseEntity.ok(ResponseDto.<List<FunzioneDto>>builder()
                .code(HttpStatus.OK.value())
                .body(funzioneService.getAllFunzioni())
                .build());
    }

    @Operation(summary = "API per convertire la qualifica pofilo di SDI")
    @GetMapping("/mapping-qualifica-profilo")
    public ResponseEntity<ResponseDto<FunzioneDto>> getFunzioneByMappingQualificaProfilo(@RequestParam String codiceQualificaProfilo){

        return ResponseEntity.ok(ResponseDto.<FunzioneDto>builder()
                .code(HttpStatus.OK.value())
                .body(funzioneService.getFunzioneByMappingQualificaProfilo(codiceQualificaProfilo))
                .build());
    }

}
