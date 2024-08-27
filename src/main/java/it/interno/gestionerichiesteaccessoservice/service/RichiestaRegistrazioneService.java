package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.DatiPrepopolamentoDto;
import it.interno.gestionerichiesteaccessoservice.dto.OrarioLavoroDto;
import it.interno.gestionerichiesteaccessoservice.dto.RichiestaRegistrazioneDto;
import it.interno.gestionerichiesteaccessoservice.dto.filtro.RicercaRichiestaFiltroDto;
import it.interno.gestionerichiesteaccessoservice.entity.Users;
import org.springframework.data.domain.Page;

public interface RichiestaRegistrazioneService {

    String getNextProtocollo();
    boolean checkEsistenzaRichiesta(String codiceFiscale);
    RichiestaRegistrazioneDto getRichiestaRegistrazione(String codiceFiscale, String protocollo);
    RichiestaRegistrazioneDto inserimentoRichiesta(RichiestaRegistrazioneDto input);
    RichiestaRegistrazioneDto aggiornamentoRichiesta(RichiestaRegistrazioneDto input);
    Page<RichiestaRegistrazioneDto> getRichiesteByStatoFiltrate(Integer idStato, RicercaRichiestaFiltroDto filtro, String ufficioOperatore, String ruoloOperatore);
    String fileDownloaded(String protocollo);

    Users approvaRichiesta(String protocollo, OrarioLavoroDto orarioLavoro,
                           String idFunzione, String note, String utenteInserimento, String ufficioinserimento);
    RichiestaRegistrazioneDto rifiutaRichiesta(String protocollo, Integer idMotivazioneRifiuto, String note);
    Integer countRichiesteByStato(Integer stato, String ufficioOperatore, String ruoloOperatore);
    DatiPrepopolamentoDto prepopolamentoRichiesta(String codiceFiscale);
    boolean checkComandantePresenteByUfficio(String codiceUfficio);
}
