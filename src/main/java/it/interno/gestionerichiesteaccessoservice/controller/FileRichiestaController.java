package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.FileRichiestaDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.service.FileRichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/file-richiesta", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "File Richiesta")
public class FileRichiestaController {

    @Autowired
    private FileRichiestaService fileRichiestaService;

    @Operation(summary = "API per recuperare il file dato il protocollo")
    @GetMapping("/download/{protocollo}")
    public ResponseEntity<ResponseDto<FileRichiestaDto>> getFileRichiestaByProtocollo(@PathVariable String protocollo){

        FileRichiestaDto result = fileRichiestaService.getFileRichiestaByProtocollo(protocollo);

        return ResponseEntity.ok(ResponseDto.<FileRichiestaDto>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

    @Operation(summary = "API per fare l'upload del file della richiesta")
    @PostMapping("/upload")
    public ResponseEntity<ResponseDto<FileRichiestaDto>> uploadFileRichiesta(@RequestBody FileRichiestaDto input){

        FileRichiestaDto result = fileRichiestaService.uploadFileRichiesta(input);

        return ResponseEntity.ok(ResponseDto.<FileRichiestaDto>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

}
