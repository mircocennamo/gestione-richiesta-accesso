package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.FileRichiestaDto;

public interface FileRichiestaService {

    FileRichiestaDto getFileRichiestaByProtocollo(String protocollo);
    FileRichiestaDto uploadFileRichiesta(FileRichiestaDto input);

}
