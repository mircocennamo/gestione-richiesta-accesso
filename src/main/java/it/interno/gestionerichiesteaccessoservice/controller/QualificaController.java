package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.QualificaDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.service.QualificaService;
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
@RequestMapping(value = "/qualifica", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Qualifica")
public class QualificaController {

    @Autowired
    private QualificaService qualificaService;

    @Operation(summary = "API per recuperare la lista di qualifiche")
    @GetMapping
    public ResponseEntity<ResponseDto<List<QualificaDto>>> getQualificaByCategoriaAndEnte(@RequestParam String categoria,
                                                                                          @RequestParam Integer idEnte){
        return ResponseEntity.ok(ResponseDto.<List<QualificaDto>>builder()
                .code(HttpStatus.OK.value())
                .body(qualificaService.getQualificaByCategoriaAndEnte(categoria, idEnte))
                .build());
    }

    @Operation(summary = "API per recuperare la categoria della qualifica in base all'ente")
    @GetMapping("/categoria")
    public ResponseEntity<ResponseDto<List<String>>> getCategoriaQualificaByEnte(@RequestParam Integer idEnte){
        return ResponseEntity.ok(ResponseDto.<List<String>>builder()
                .code(HttpStatus.OK.value())
                .body(qualificaService.getCategoriaQualificaByEnte(idEnte))
                .build());
    }

    @Operation(summary = "API per recuperare la lista di ruoli delle qualifiche")
    @GetMapping("/ruolo")
    public ResponseEntity<ResponseDto<List<String>>> getRuoloQualifica(){
        return ResponseEntity.ok(ResponseDto.<List<String>>builder()
                .code(HttpStatus.OK.value())
                .body(qualificaService.getRuoloQualifica())
                .build());
    }

}
