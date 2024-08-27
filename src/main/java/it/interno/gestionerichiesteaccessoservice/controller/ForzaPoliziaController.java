package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.ForzaPoliziaDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.service.ForzaPoliziaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/forza-polizia", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Forza Polizia")
public class ForzaPoliziaController {

    @Autowired
    private ForzaPoliziaService forzaPoliziaService;

    @Operation(summary = "API per recuperare tutte le forze di polizia")
    @GetMapping
    public ResponseEntity<ResponseDto<List<ForzaPoliziaDto>>> getAllForzePolizia(){

        List<ForzaPoliziaDto> result = forzaPoliziaService.getAll();

        return ResponseEntity.ok(ResponseDto.<List<ForzaPoliziaDto>>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

}
