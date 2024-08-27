package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.MotivazioneRifiutoDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.service.MotivazioneRifiutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/motivazione-rifiuto", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Motivazione Rifiuto")
public class MotivazioneRifiutoController {

    @Autowired
    private MotivazioneRifiutoService motivazioneRifiutoService;

    @Operation(summary = "API per recuperare tutte le motivazioni di rifiuto")
    @GetMapping
    public ResponseEntity<ResponseDto<List<MotivazioneRifiutoDto>>> getAllMotivazioniRifiuto(){

        List<MotivazioneRifiutoDto> result = motivazioneRifiutoService.getAll();

        return ResponseEntity.ok(ResponseDto.<List<MotivazioneRifiutoDto>>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }
}
