package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.dto.FileRichiestaDto;
import it.interno.gestionerichiesteaccessoservice.entity.FileRichiesta;
import it.interno.gestionerichiesteaccessoservice.entity.RichiestaRegistrazione;
import it.interno.gestionerichiesteaccessoservice.entity.StatoRichiesta;
import it.interno.gestionerichiesteaccessoservice.exceptions.ObjectNotFoundException;
import it.interno.gestionerichiesteaccessoservice.mapper.FileRichiestaMapper;
import it.interno.gestionerichiesteaccessoservice.repository.RichiestaRegistrazioneRepository;
import it.interno.gestionerichiesteaccessoservice.utils.ConversionUtils;
import it.interno.gestionerichiesteaccessoservice.utils.GenericUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.interno.gestionerichiesteaccessoservice.repository.FileRichiestaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileRichiestaServiceImpl implements FileRichiestaService{

    @Autowired
    private FileRichiestaMapper fileRichiestaMapper;
    @Autowired
    private FileRichiestaRepository fileRichiestaRepository;
    @Autowired
    private RichiestaRegistrazioneRepository richiestaRegistrazioneRepository;

    @Override
    public FileRichiestaDto getFileRichiestaByProtocollo(String protocollo) {
        return fileRichiestaMapper.toDto(fileRichiestaRepository.getByProtocollo(protocollo));
    }

    @Override
    @Transactional
    public FileRichiestaDto uploadFileRichiesta(FileRichiestaDto input) {

        FileRichiesta fileRichiesta = fileRichiestaMapper.toEntity(input);

        fileRichiesta.setId(fileRichiestaRepository.getNextId());
        fileRichiesta.setNome(fileRichiesta.getId() + "/" + fileRichiesta.getFilename());
        fileRichiesta.setApplicationId(101);
        fileRichiesta.setDataCreazione(ConversionUtils.getCurrentTimestamp());

        // Elimino il vecchio file se esiste
        FileRichiesta fr = fileRichiestaRepository.getByProtocollo(input.getRichiestaRegistrazione().getIdProtocollo());

        if(fr != null){
            fr.setFilename(fileRichiesta.getFilename());
            fr.setNome(fr.getId() + "/" + fr.getFilename());
            fr.setApplicationId(101);
            fr.setDataCreazione(ConversionUtils.getCurrentTimestamp());
            fr.setBlob(fileRichiesta.getBlob());
            fr.setMimeType(fileRichiesta.getMimeType());
            fileRichiesta = fr;
        }

        // Aggiorno lo stato della richiesta
        RichiestaRegistrazione richiesta = richiestaRegistrazioneRepository.getRichiestaByProtocollo(fileRichiesta.getRichiestaRegistrazione().getIdProtocollo());

        if(richiesta == null)
            throw new ObjectNotFoundException("La richiesta di registrazione non è presente.");

        richiesta.setStatoRichiesta(new StatoRichiesta(2, null));
        richiestaRegistrazioneRepository.save(richiesta);

        fileRichiesta = fileRichiestaRepository.save(fileRichiesta);

        // INVIO MAIL AL FP
        GenericUtils.sendMailFocalPoint(richiesta.getUfficio().getCodiceUfficio(), richiesta.getIdProtocollo(), richiestaRegistrazioneRepository);

        // INVIO MAIL AL COMANDANTE
        String emailComandante = richiestaRegistrazioneRepository.getComandanteEmailByUfficio(richiesta.getUfficio().getCodiceUfficio());
        if (!StringUtils.isBlank(emailComandante))
            GenericUtils.sendMail(
                    emailComandante,
                    "Nuova richiesta di creazione utenza sottomessa.",
                    "Una nuova richiesta di creazione utenza con protocollo " + richiesta.getIdProtocollo() + " è stata inserita.",
                    richiestaRegistrazioneRepository
            );

        return fileRichiestaMapper.toDto(fileRichiesta);
    }
}
