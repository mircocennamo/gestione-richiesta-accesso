package it.interno.gestionerichiesteaccessoservice.service;

import it.interno.gestionerichiesteaccessoservice.client.OimClient;
import it.interno.gestionerichiesteaccessoservice.client.PersonaFisicaClient;
import it.interno.gestionerichiesteaccessoservice.dto.*;
import it.interno.gestionerichiesteaccessoservice.dto.filtro.RicercaRichiestaFiltroDto;
import it.interno.gestionerichiesteaccessoservice.dto.oim.UtenteOimDto;
import it.interno.gestionerichiesteaccessoservice.entity.*;
import it.interno.gestionerichiesteaccessoservice.exceptions.*;
import it.interno.gestionerichiesteaccessoservice.mapper.RichiestaRegistrazioneMapper;
import it.interno.gestionerichiesteaccessoservice.repository.*;
import it.interno.gestionerichiesteaccessoservice.utils.ConversionUtils;
import it.interno.gestionerichiesteaccessoservice.utils.GenericUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class RichiestaRegistrazioneServiceImpl implements RichiestaRegistrazioneService{

    @Autowired
    private RichiestaRegistrazioneMapper richiestaRegistrazioneMapper;
    @Autowired
    private RichiestaRegistrazioneRepository richiestaRegistrazioneRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private FileRichiestaRepository fileRichiestaRepository;
    @Autowired
    private UsersOrarioLavoroRepository usersOrarioLavoroRepository;
    @Autowired
    private UtenteUfficioRepository utenteUfficioRepository;
    @Autowired
    private LuogoRepository luogoRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MotivazioneRifiutoRepository motivazioneRifiutoRepository;
    @Autowired
    private QualificaRepository qualificaRepository;
    @Autowired
    private UfficioRepository ufficioRepository;
    @Autowired
    private ComandanteUfficiRepository comandanteUfficiRepository;
    @Autowired
    private FunzioneRepository funzioneRepository;
    @Autowired
    private OimClient oimClient;
    @Autowired
    private ForzaPoliziaRepository forzaPoliziaRepository;

    @Autowired
    private PersonaFisicaClient personaFisicaClient;

    @Override
    public String getNextProtocollo() {
        return richiestaRegistrazioneRepository.getNextProtocollo();
    }

    @Override
    // RITORNA TRUE SE PUO' PROCEDERE ALL'INSERIMENTO DEL PROTOCOLLO,
    // FALSE SE DEVE INSERIRE UNA NUOVA RICHIESTA
    public boolean checkEsistenzaRichiesta(String codiceFiscale) {

        if(StringUtils.isBlank(codiceFiscale))
            throw new CodiceFiscaleNonValidoException("Il codice fiscale inserito non è valido.");
        if(!GenericUtils.isMaggiorenne(codiceFiscale))
            throw new CodiceFiscaleNonValidoException("Il codice fiscale corrisponde ad un minore.");

        List<RichiestaRegistrazione> richiesteValide = richiestaRegistrazioneRepository.getRichiestaByCodiceFiscale(codiceFiscale.toUpperCase());
        Users utente = usersRepository.getUserByCodiceFiscale(codiceFiscale.toUpperCase());

        // Se ne la richiesta ne l'utente sono presenti allora si può creare una richiesta nuova
        if(richiesteValide.isEmpty() && utente == null)
            return false;
        // Se la richiesta non è presente ma l'utente si e non è cancellato allora non si può inserire una nuova richiesta
        if(richiesteValide.isEmpty() && utente.getDataCancellazione() == null)
            return true;
        // Se la richiesta non è presente ma l'utente si ed è cancellato allora si può inserire una nuova richiesta
        if(richiesteValide.isEmpty() && utente.getDataCancellazione() != null)
            return false;
        // Se la richiesta è presente ma l'utente no, allora non si può creare una nuova richiesta
        if(!richiesteValide.isEmpty() && utente == null)
            return true;
        // Se la richiesta è presente e anche l'utente non cancellato, allora non si può creare una nuova richiesta
        if(!richiesteValide.isEmpty() && utente.getDataCancellazione() == null)
            return true;
        // Se la richiesta è presente e anche l'utente cancellato, allora si può creare una nuova richiesta
        if(!richiesteValide.isEmpty() && utente.getDataCancellazione() != null)
            return false;

        return true;
    }

    @Override
    public RichiestaRegistrazioneDto getRichiestaRegistrazione(String codiceFiscale, String protocollo) {

        RichiestaRegistrazione richiesta = richiestaRegistrazioneRepository.getRichiesta(codiceFiscale, protocollo);

        if(richiesta == null)
            throw new RichiestaNotFoundException();

        return richiestaRegistrazioneMapper.toDto(richiesta);
    }

    private PersonaFisicaDto convert(RichiestaRegistrazioneDto richiestaRegistrazioneDto){
        PersonaFisicaDto personaFisicaDto = new PersonaFisicaDto();
        personaFisicaDto.setCodiceFiscale(richiestaRegistrazioneDto.getCodiceFiscale());
        personaFisicaDto.setCognome(richiestaRegistrazioneDto.getCognome());
        personaFisicaDto.setNome(richiestaRegistrazioneDto.getNome());
        personaFisicaDto.setDataNascita(richiestaRegistrazioneDto.getDataNascita());
        personaFisicaDto.setSesso(String.valueOf(richiestaRegistrazioneDto.getSesso()));
        personaFisicaDto.setCodiceLuogoNascita(richiestaRegistrazioneDto.getCodiceLuogoNascita());


        return personaFisicaDto;
    }

    @Override
    @Transactional
    public RichiestaRegistrazioneDto inserimentoRichiesta(RichiestaRegistrazioneDto input) {

        //invoco persone fisiche per validazione Codice fiscale bug numero 2
        ResponseEntity<ResponseDto<Boolean>> verificaCF = personaFisicaClient.verificaCodiceFiscale(convert(input));
        if(verificaCF.getBody().getBody() == null || !verificaCF.getBody().getBody()){
            throw new CodiceFiscaleNonValidoException("Il codice fiscale inserito non è valido.");
        }
        RichiestaRegistrazione richiesta = richiestaRegistrazioneMapper.toEntity(input);

        try{
            richiesta.setDataRichiesta(ConversionUtils.getCurrentTimestamp());
            richiesta.setStatoRichiesta(new StatoRichiesta(1, null));
            richiesta.setFlagDownload(0);
            richiesta = richiestaRegistrazioneRepository.save(richiesta);
        }catch(Exception ex){
            GenericUtils.sendMail(
                    richiesta.getEmailPrimaria() == 0 ? richiesta.getEmail() : richiesta.getEmailPrivata(),
                    "Errore inserimento richiesta creazione utenza",
                    "La richiesta con protocollo n. " + richiesta.getIdProtocollo() + " non e' stata registrata.",
                    richiestaRegistrazioneRepository
            );

            throw new RichiestaNonInseritaException();
        }

        GenericUtils.sendMail(
                richiesta.getEmailPrimaria() == 0 ? richiesta.getEmail() : richiesta.getEmailPrivata(),
                "Conferma richiesta di creazione utenza",
                "La richiesta con protocollo " + richiesta.getIdProtocollo() + " e' stata registrata correttamente.",
                richiestaRegistrazioneRepository
        );

        return richiestaRegistrazioneMapper.toDto(richiesta);
    }

    @Override
    @Transactional
    public RichiestaRegistrazioneDto aggiornamentoRichiesta(RichiestaRegistrazioneDto input) {

        //invoco persone fisiche per validazione Codice fiscale bug numero 2
        ResponseEntity<ResponseDto<Boolean>> verificaCF = personaFisicaClient.verificaCodiceFiscale(convert(input));
        if(verificaCF.getBody().getBody() == null || !verificaCF.getBody().getBody()){
            throw new CodiceFiscaleNonValidoException("Il codice fiscale inserito non è valido.");
        }

        RichiestaRegistrazione suDb = richiestaRegistrazioneRepository.getRichiestaByProtocollo(input.getIdProtocollo());

        if(suDb.getStatoRichiesta().getIdStato() != 1)
            throw new RichiestaNonAggiornabileException();

        RichiestaRegistrazione richiesta = richiestaRegistrazioneMapper.toEntity(input);
        richiesta.setDataAggiornamento(ConversionUtils.getCurrentTimestamp());
        richiesta.setStatoRichiesta(new StatoRichiesta(1, null));

        // Eliminazione file obsoleto
        richiesta.setFlagDownload(0);
        fileRichiestaRepository.deleteFileByProtocollo(input.getIdProtocollo());

        return richiestaRegistrazioneMapper.toDto(richiestaRegistrazioneRepository.save(richiesta));
    }

    @Override
    public Page<RichiestaRegistrazioneDto> getRichiesteByStatoFiltrate(Integer idStato, RicercaRichiestaFiltroDto filtro, String ufficioOperatore, String ruoloOperatore) {

        Pageable pageable = PageRequest.of(
                filtro.getPaginazione().getPagina(),
                filtro.getPaginazione().getNumeroElementi(),
                Sort.by(new Sort.Order(
                        Sort.Direction.fromString(StringUtils.isBlank(filtro.getPaginazione().getOrderDirection()) ? "ASC" : filtro.getPaginazione().getOrderDirection()),
                        StringUtils.isBlank(filtro.getPaginazione().getOrderBy()) ? "cognome" : filtro.getPaginazione().getOrderBy()
                ))
        );

        Page<RichiestaRegistrazione> page = richiestaRegistrazioneRepository.getRichiesteByStatoFilrate(
                idStato,
                filtro.getForzaPolizia(),
                filtro.getCodiceUfficio(),
                filtro.getNome_cognome(),
                ufficioOperatore,
                ruoloOperatore,
                pageable
        );

        List<RichiestaRegistrazioneDto> result = page.stream()
                .map(el -> richiestaRegistrazioneMapper.toDto(el))
                .toList();

        return new PageImpl<>(result, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public Users approvaRichiesta(String protocollo, OrarioLavoroDto orarioLavoro,
                                                      String idFunzione, String note,  String utenteInserimento, String ufficioInserimento) {

        Users utenteDaCreare = null;
        RichiestaRegistrazione richiesta = richiestaRegistrazioneRepository.getRichiestaByProtocollo(protocollo);

        if (richiesta == null)
            throw new RichiestaNotFoundException();

        try {

            // Funzione di recupero generazione codiceUtente
            String codiceUtente = richiestaRegistrazioneRepository.getCodiceUtenteCreazione(richiesta.getCodiceFiscale(), richiesta.getForzaPolizia().getIdGruppo());

            // Creazione orario lavoro
            Timestamp ora = ConversionUtils.getCurrentTimestamp();
            UsersOrarioLavoro usersOrarioLavoro = new UsersOrarioLavoro(
                    codiceUtente,
                    richiesta.getUfficio().getCodiceUfficio(), ora, utenteInserimento, ufficioInserimento,
                    null);

            usersOrarioLavoro.setOrario(orarioLavoro);
            usersOrarioLavoroRepository.save(usersOrarioLavoro);

            // Assegnazione Comandante se Autodichiarato
            if(richiesta.getComandanteAutodichiarato().equalsIgnoreCase("SI")){

                if(comandanteUfficiRepository.getComandanteByUfficio(richiesta.getUfficio().getCodiceUfficio()) != null){
                    throw new ComandanteGiaPresenteException("Il comandante è già presente, l'utente non può essere assegnato comandante.");
                }

                comandanteUfficiRepository.save(new ComandanteUffici(codiceUtente, richiesta.getUfficio().getCodiceUfficio(), ora, utenteInserimento, ufficioInserimento, null));
            }

            // Creazione User
            Luogo luogo = luogoRepository.getByDescrizioneAndInLuogo(richiesta.getLuogoNascita(), new String[]{"01", "04"}, richiesta.getDataNascita());

            utenteDaCreare = new Users(
                    codiceUtente,
                    richiesta.getUfficio().getCodiceUfficio(),
                    richiesta.getForzaPolizia().getIdGruppo(),
                    luogo.getCodiceRegione(),
                    luogo.getCodiceProvincia(),
                    richiesta.getNome(),
                    richiesta.getCognome(),
                    richiesta.getDataNascita(),
                    richiesta.getSesso(),
                    richiesta.getLuogoNascita(),
                    richiesta.getCodiceFiscale(),
                    richiesta.getEmail(),
                    richiesta.getTelefono(),
                    richiesta.getPrefissoTelefono(),
                    richiesta.getEmailPrivata(),
                    utenteInserimento,
                    ufficioInserimento,
                    ora,
                    richiesta.getEmailPrimaria(),
                    luogo.getSiglaProvincia(),
                    1,
                    richiesta.getPaeseDiNascita(),
                    richiesta.getQualifica().getIdQualifica(),
                    idFunzione,
                    new Luogo(luogo.getCodiceLuogo()),
                    note
            );

            utenteDaCreare = usersRepository.save(utenteDaCreare);

            // Aggiornamento stato e data richiesta
            richiesta.setStatoRichiesta(new StatoRichiesta(3, null));
            richiesta.setDataAggiornamento(ora);
            richiestaRegistrazioneRepository.save(richiesta);

            // Inserimento su tabella SDI
            if(utenteRepository.getUtenteByCodiceUtente(codiceUtente) == null)
                richiestaRegistrazioneRepository.inserimentoTsiUtenteOracle(
                        codiceUtente,
                        StringUtils.isBlank(utenteDaCreare.getPrefissoTelefono()) ? null : utenteDaCreare.getPrefissoTelefono() + utenteDaCreare.getTelefono(),
                        utenteDaCreare.getCodiceFiscale(),
                        utenteDaCreare.getCognome(),
                        utenteDaCreare.getNome(),
                        utenteDaCreare.getSesso(),
                        utenteDaCreare.getDataNascita(),
                        utenteDaCreare.getCodiceLuogoNascita().getCodiceLuogo(),
                        utenteDaCreare.getLuogoNascita(),
                        utenteDaCreare.getSiglaProvinciaNacita(),
                        ora.toString(),
                        utenteInserimento,
                        ufficioInserimento,
                        utenteDaCreare.getQualifica(),
                        utenteDaCreare.getIdTipoUtente(),
                        utenteDaCreare.getEmailPrimaria() == 0 ? utenteDaCreare.getEmail() : utenteDaCreare.getEmailPrivata()
                );

            Qualifica qualifica = qualificaRepository.findById(utenteDaCreare.getQualifica()).orElse(null);
            oimClient.creazioneUtente(new UtenteOimDto(
                    utenteDaCreare.getCodiceUtente(),
                    utenteDaCreare.getCognome(),
                    utenteDaCreare.getNome(),
                    utenteDaCreare.getEmailPrimaria() == 0 ? utenteDaCreare.getEmail() : utenteDaCreare.getEmailPrivata(),
                    utenteDaCreare.getCodiceUfficio(),
                    ufficioRepository.getUfficioById(utenteDaCreare.getCodiceUfficio()).getDescrizioneUfficio(), // descrizione ufficio
                    utenteDaCreare.getTelefono(),
                    qualifica == null ? null : ConversionUtils.getDescrizioneQualifica(qualifica, utenteDaCreare.getForzaPolizia()), // descrizione qualifica
                    qualifica == null ? null : qualifica.getCategoria(), // categoria
                    forzaPoliziaRepository.findById(utenteDaCreare.getForzaPolizia()).orElse(new ForzaPolizia()).getNome(), // descrizione ente
                    utenteDaCreare.getDataNascita(),
                    utenteDaCreare.getCodiceLuogoNascita().getDescrizioneLuogo(),
                    utenteDaCreare.getSesso().toString(),
                    utenteDaCreare.getCodiceFiscale(),
                    utenteDaCreare.getEmailPrimaria() == 0 ? utenteDaCreare.getEmailPrivata() : utenteDaCreare.getEmail()
            ));

        }catch(ComandanteGiaPresenteException ex){
            richiestaRegistrazioneRepository.scritturaErroreCreazione(richiesta.getIdProtocollo(), ex.getMessage(), LocalDate.now());
            throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            richiestaRegistrazioneRepository.scritturaErroreCreazione(richiesta.getIdProtocollo(), ex.getMessage(), LocalDate.now());
            throw ex;
        }

        return utenteDaCreare;
    }

    @Override
    @Transactional
    public RichiestaRegistrazioneDto rifiutaRichiesta(String protocollo, Integer idMotivazioneRifiuto, String note) {

        RichiestaRegistrazione richiesta = richiestaRegistrazioneRepository.getRichiestaByProtocollo(protocollo);

        if(richiesta == null)
            throw new RichiestaNotFoundException();

        richiesta.setStatoRichiesta(new StatoRichiesta(4, null));
        richiesta.setMotivazioneRifiuto(new MotivazioneRifiuto(idMotivazioneRifiuto, null));
        richiesta.setNote(note);
        richiesta.setDataAggiornamento(ConversionUtils.getCurrentTimestamp());

        richiesta = richiestaRegistrazioneRepository.save(richiesta);

        MotivazioneRifiuto motivazione = motivazioneRifiutoRepository.findById(idMotivazioneRifiuto).orElse(null);

        if(motivazione == null)
            throw new ObjectNotFoundException("La motivazione specificata non è corretta.");

        GenericUtils.sendMail(
                richiesta.getEmailPrimaria() == 0 ? richiesta.getEmail() : richiesta.getEmailPrivata(),
                "Richiesta creazione utenza rifiutata",
                "La richiesta con protocollo " + richiesta.getIdProtocollo() + " e' stata rifiutata. \n\n" +
                        "Motivazione: " + motivazione.getMotivazione() + "\n" +
                        "Note: " + richiesta.getNote(),
                richiestaRegistrazioneRepository
        );

        return richiestaRegistrazioneMapper.toDto(richiesta);
    }

    @Override
    public String fileDownloaded(String protocollo) {

        RichiestaRegistrazione richiesta = richiestaRegistrazioneRepository.getRichiestaByProtocollo(protocollo);
        richiesta.setFlagDownload(1);
        richiestaRegistrazioneRepository.save(richiesta);

        Utente utente = utenteRepository.getUtenteByCodiceFiscale(richiesta.getCodiceFiscale());

        return utente != null ? utente.getIdUtente() : null;
    }

    @Override
    public Integer countRichiesteByStato(Integer stato, String ufficioOperatore, String ruoloOperatore) {
        return richiestaRegistrazioneRepository.countRichiesteByStato(stato, ufficioOperatore, ruoloOperatore);
    }

    @Override
    public DatiPrepopolamentoDto prepopolamentoRichiesta(String codiceFiscale) {

        Users utenteSicurezza = usersRepository.getUserByCodiceFiscale(codiceFiscale);

        if(utenteSicurezza != null){
            DatiPrepopolamentoDto dati = new DatiPrepopolamentoDto(utenteSicurezza);

            // CALCOLO DESCRIZIONE UFFICIO
            if(!StringUtils.isBlank(dati.getCodiceUfficio())){
                Ufficio ufficio = ufficioRepository.getUfficioById(dati.getCodiceUfficio());
                dati.setUfficio(ufficio == null ? null : ufficio.getDescrizioneUfficio());
            }

            // CALCOLO CATEGORIA QUALIFICA
            if(dati.getCodiceQualifica() != null){
                Qualifica qualifica = qualificaRepository.findById(dati.getCodiceQualifica()).orElse(null);
                dati.setCategoria(qualifica == null ? null : qualifica.getCategoria());
            }

            // CALCOLO ORARIO LAVORO
            UsersOrarioLavoro orarioLavoro = usersOrarioLavoroRepository.getOrarioLavoroByUtenteUfficio(utenteSicurezza.getCodiceUtente(), utenteSicurezza.getCodiceUfficio());
            if(orarioLavoro != null)
                dati.setOrarioLavoro(new OrarioLavoroDto(
                        orarioLavoro.getLunediDa(),
                        orarioLavoro.getLunediA(),
                        orarioLavoro.getMartediDa(),
                        orarioLavoro.getMartediA(),
                        orarioLavoro.getMercolediDa(),
                        orarioLavoro.getMercolediA(),
                        orarioLavoro.getGiovediDa(),
                        orarioLavoro.getGiovediA(),
                        orarioLavoro.getVenerdiDa(),
                        orarioLavoro.getVenerdiA(),
                        orarioLavoro.getSabatoDa(),
                        orarioLavoro.getSabatoA(),
                        orarioLavoro.getDomenicaDa(),
                        orarioLavoro.getDomenicaA()
                ));


            return dati;
        }

        Utente utenteSdi = utenteRepository.getUtenteByCodiceFiscale(codiceFiscale);
        if(utenteSdi != null){
            UtenteUfficio ufficioUtenteSdi = utenteUfficioRepository.getByIdUtente(utenteSdi.getIdUtente());
            DatiPrepopolamentoDto dati = new DatiPrepopolamentoDto(utenteSdi, ufficioUtenteSdi);

            // CALCOLO ID ENTE E DESCRIZIONE UFFICIO
            if(!StringUtils.isBlank(dati.getCodiceUfficio())){
                Ufficio ufficio = ufficioRepository.getUfficioById(dati.getCodiceUfficio());
                dati.setIdEnte(ufficio == null ? null : ufficio.getForzaPolizia());
                dati.setUfficio(ufficio == null ? null : ufficio.getDescrizioneUfficio());
            }

            // CALCOLO MAPPING FUNZIONE
            Funzione funzione = funzioneRepository.getFunzioneByMappingQualificaProfilo(utenteSdi.getCGra());
            if(funzione != null)
                dati.setFunzione(funzione.getIdFunzione());

            return dati;
        }

        return null;
    }

    @Override
    public boolean checkComandantePresenteByUfficio(String codiceUfficio) {
        return comandanteUfficiRepository.getComandanteByUfficio(codiceUfficio) != null;
    }
}
