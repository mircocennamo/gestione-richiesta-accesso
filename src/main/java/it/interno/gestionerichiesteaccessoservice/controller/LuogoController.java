package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.LuogoDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.service.LuogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/luogo", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Luogo")
public class LuogoController {

    @Autowired
    private LuogoService luogoService;

    @Operation(summary = "API per l'autocomplete del luogo")
    @GetMapping("/autocomplete")
    public ResponseEntity<ResponseDto<List<LuogoDto>>> getLuogoByDescrizioneLike(@RequestParam String descrizione,
                                                                                  @RequestParam String[] inLuogo,
                                                                                  @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam LocalDate dataRif){

        return ResponseEntity.ok(ResponseDto.<List<LuogoDto>>builder()
                .code(HttpStatus.OK.value())
                .body(luogoService.getLuogoByDescrizioneLike(descrizione, inLuogo, dataRif))
                .build());
    }

}
