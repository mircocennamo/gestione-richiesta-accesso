package it.interno.gestionerichiesteaccessoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.gestionerichiesteaccessoservice.dto.DatiPrepopolamentoDto;
import it.interno.gestionerichiesteaccessoservice.dto.OrarioLavoroDto;
import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.dto.RichiestaRegistrazioneDto;
import it.interno.gestionerichiesteaccessoservice.dto.filtro.RicercaRichiestaFiltroDto;
import it.interno.gestionerichiesteaccessoservice.entity.Users;
import it.interno.gestionerichiesteaccessoservice.service.RichiestaRegistrazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/richiesta-registrazione", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Richiesta Registrazione")
public class RichiestaRegistrazioneController {

    @Autowired
    private RichiestaRegistrazioneService richiestaRegistrazioneService;

    @Operation(summary = "API per controllare se esiste una richiesta o se è possibile crearla", description = "Ritorna TRUE se esiste già una richiesta valida, FALSE se è possibile crearne una.")
    @GetMapping("/{codiceFiscale}")
    public ResponseEntity<ResponseDto<Boolean>> checkRichiestaRegistrazione(@PathVariable String codiceFiscale){
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .code(HttpStatus.OK.value())
                .body(richiestaRegistrazioneService.checkEsistenzaRichiesta(codiceFiscale.toUpperCase()))
                .build());
    }

    @Operation(summary = "API per recuperare la richiesta di registrazione tramite CF e PROTOCOLLO")
    @GetMapping("/ricerca")
    public ResponseEntity<ResponseDto<RichiestaRegistrazioneDto>> getRichiestaRegistrazione(@RequestParam String codiceFiscale,
                                                                                            @RequestParam String protocollo){

        RichiestaRegistrazioneDto result = richiestaRegistrazioneService.getRichiestaRegistrazione(codiceFiscale.toUpperCase(), protocollo);

        return ResponseEntity.ok(ResponseDto.<RichiestaRegistrazioneDto>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

    @Operation(summary = "API per recuperare il prossimo protocollo utile")
    @GetMapping("/protocollo")
    public ResponseEntity<ResponseDto<String>> getNextProtocollo(){
        return ResponseEntity.ok(ResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .body(richiestaRegistrazioneService.getNextProtocollo())
                .build());
    }

    @Operation(summary = "API l'inserimento di una nuova richiesta")
    @PostMapping
    public ResponseEntity<ResponseDto<RichiestaRegistrazioneDto>> inserimentoRichiesta(@RequestBody RichiestaRegistrazioneDto input){
        //TODO aggiungere controllo coerenza codice fiscale (punto 2 della lista)
        RichiestaRegistrazioneDto result = richiestaRegistrazioneService.inserimentoRichiesta(input);

        return ResponseEntity.ok(ResponseDto.<RichiestaRegistrazioneDto>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

    @Operation(summary = "API l'aggiornamento di una nuova richiesta")
    @PutMapping
    public ResponseEntity<ResponseDto<RichiestaRegistrazioneDto>> aggiornamentoRichiesta(@RequestBody RichiestaRegistrazioneDto input){
    //TODO aggiungere controllo coerenza codice fiscale (punto 2 della lista)
        RichiestaRegistrazioneDto result = richiestaRegistrazioneService.aggiornamentoRichiesta(input);

        return ResponseEntity.ok(ResponseDto.<RichiestaRegistrazioneDto>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

    @Operation(summary = "API per l'approvazione della richiesta")
    @PostMapping("/approvazione/{protocollo}")
    public ResponseEntity<ResponseDto<Users>> approvaRichiesta(@PathVariable String protocollo,
                                                               @RequestParam String idFunzione,
                                                               @RequestParam(required = false) String note,
                                                               @RequestParam String utenteInserimento,
                                                               @RequestParam String ufficioinserimento,
                                                               @RequestBody OrarioLavoroDto orarioLavoro){

        Users result = richiestaRegistrazioneService.approvaRichiesta(protocollo, orarioLavoro, idFunzione, note, utenteInserimento, ufficioinserimento);

        return ResponseEntity.ok(ResponseDto.<Users>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

    @Operation(summary = "API per il rifiuto della richiesta")
    @PostMapping("/rifiuto/{protocollo}/{idMotivazioneRifiuto}")
    public ResponseEntity<ResponseDto<RichiestaRegistrazioneDto>> rifiutaRichiesta(@PathVariable String protocollo,
                                                                                   @PathVariable Integer idMotivazioneRifiuto,
                                                                                   @RequestBody String note){

        RichiestaRegistrazioneDto result = richiestaRegistrazioneService.rifiutaRichiesta(protocollo, idMotivazioneRifiuto, note);

        return ResponseEntity.ok(ResponseDto.<RichiestaRegistrazioneDto>builder()
                .code(HttpStatus.OK.value())
                .body(result)
                .build());
    }

    @Operation(summary = "API per recuperare le richieste dato lo stato")
    @PostMapping("/by-stato/{idStato}")
    public ResponseEntity<ResponseDto<Page<RichiestaRegistrazioneDto>>> getRichiesteByStatoFilrate(@PathVariable Integer idStato,
                                                                                            @RequestParam String ufficioOperatore,
                                                                                            @RequestParam String ruoloOperatore,
                                                                                            @RequestBody RicercaRichiestaFiltroDto filtro){
        return ResponseEntity.ok(ResponseDto.<Page<RichiestaRegistrazioneDto>>builder()
                .code(HttpStatus.OK.value())
                .body(richiestaRegistrazioneService.getRichiesteByStatoFiltrate(idStato, filtro, ufficioOperatore, ruoloOperatore))
                .build());
    }

    @Operation(summary = "API per recuperare le richieste dato lo stato")
    @GetMapping("/file-dowloaded/{protocollo}")
    public ResponseEntity<ResponseDto<String>> fileDownloaded(@PathVariable String protocollo){

        return ResponseEntity.ok(
                ResponseDto.<String>builder()
                        .code(HttpStatus.OK.value())
                        .body(richiestaRegistrazioneService.fileDownloaded(protocollo))
                        .build());
    }

    @Operation(summary = "API per contare le richieste dato lo stato")
    @GetMapping("/count-by-stato/{stato}")
    public ResponseEntity<ResponseDto<Integer>> countRichiesteByStato(@PathVariable Integer stato,
                                                                      @RequestParam String ufficioOperatore,
                                                                      @RequestParam String ruoloOperatore){

        return ResponseEntity.ok(
                ResponseDto.<Integer>builder()
                        .code(HttpStatus.OK.value())
                        .body(richiestaRegistrazioneService.countRichiesteByStato(stato, ufficioOperatore, ruoloOperatore))
                        .build());
    }

    @Operation(summary = "API per recuperare i dati per prepopolare la richiesta")
    @GetMapping("/prepopolamento")
    public ResponseEntity<ResponseDto<DatiPrepopolamentoDto>> prepopolamentoRichiesta(@RequestParam String codiceFiscale){

        return ResponseEntity.ok(
                ResponseDto.<DatiPrepopolamentoDto>builder()
                        .code(HttpStatus.OK.value())
                        .body(richiestaRegistrazioneService.prepopolamentoRichiesta(codiceFiscale.toUpperCase()))
                        .build());
    }

    @Operation(summary = "API per controllare se un ufficio ha già un comandante")
    @GetMapping("/presenza-comandante-ufficio")
    public ResponseEntity<ResponseDto<Boolean>> checkComandantePresenteByUfficio(@RequestParam String codiceUfficio){

        return ResponseEntity.ok(
                ResponseDto.<Boolean>builder()
                        .code(HttpStatus.OK.value())
                        .body(richiestaRegistrazioneService.checkComandantePresenteByUfficio(codiceUfficio))
                        .build());
    }
}
