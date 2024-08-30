package it.interno.gestionerichiesteaccessoservice.utils;

import it.interno.gestionerichiesteaccessoservice.dto.FocalPointDto;
import it.interno.gestionerichiesteaccessoservice.repository.RichiestaRegistrazioneRepository;

import jakarta.persistence.Tuple;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericUtils {

    static void sendMail(String to, String subject, String message, RichiestaRegistrazioneRepository repository){
        try{
            repository.sendMail(
                    to,
                    subject,
                    message
            );
        }catch(Exception ex){
        }
    }

    static void sendMailToAll(List<String> to, String subject, String message, RichiestaRegistrazioneRepository repository){
        to.forEach(el -> sendMail(el, subject, message, repository));
    }

    static void sendMailFocalPoint(String codiceUfficio, String protocollo, RichiestaRegistrazioneRepository repository){

        List<Tuple> listaFocalPoint = repository.getFocalPointByUfficio(codiceUfficio);

        List<FocalPointDto> focalPoint = listaFocalPoint
                .stream()
                .map(el -> new FocalPointDto(
                        el.get("START_UFF", String.class) == null ? null : el.get("START_UFF", String.class).trim(),
                        el.get("G_MEMBER", String.class) == null ? null : el.get("G_MEMBER", String.class).trim(),
                        el.get("ID_FUNZIONE", String.class) == null ? null : el.get("ID_FUNZIONE", String.class).trim(),
                        el.get("G_UFFICIO", String.class) == null ? null : el.get("G_UFFICIO", String.class).trim(),
                        el.get("EMAIL", String.class) == null ? null : el.get("EMAIL", String.class).trim()
                )).toList();

        List<String> provinciale = focalPoint.stream().filter(el -> el.getCodiceQualifica() != null && el.getCodiceQualifica().equalsIgnoreCase("FO")).map(FocalPointDto::getEmail).toList();
        List<String> nazionale = focalPoint.stream().filter(el -> el.getCodiceQualifica() != null && el.getCodiceQualifica().equalsIgnoreCase("FN")).map(FocalPointDto::getEmail).toList();
        List<String> centrale = focalPoint.stream().filter(el ->  el.getCodiceQualifica() != null && el.getCodiceQualifica().equalsIgnoreCase("FC")).map(FocalPointDto::getEmail).toList();

        String subject = "Nuova richiesta di registrazione sottomessa.";
        String message = "Una nuova richiesta con protocollo " + protocollo + " è stata inserita";

        if(!provinciale.isEmpty())
            sendMailToAll(provinciale, subject, message, repository);
        else if(!nazionale.isEmpty())
            sendMailToAll(nazionale, subject, message, repository);
        else if(!centrale.isEmpty())
            sendMailToAll(centrale, subject, message, repository);

    }

    static boolean isMaggiorenne(String codiceFiscale){

        int annoNascita =  Integer.parseInt(codiceFiscale.substring(6, 8));
        if(annoNascita > 21)
            annoNascita += 1900;
        else
            annoNascita += 2000;
        String meseNascita = codiceFiscale.substring(8, 9);
        Mese mese = Mese.fromValue(meseNascita);
        Integer giornoNascita = Integer.parseInt(codiceFiscale.substring(9, 11));
        //se è donna sottraiamo 40 al giorno di nascita
        if(giornoNascita > 40)
            giornoNascita -= 40;
        LocalDate localDate = LocalDate.of(annoNascita, mese.getMese(), giornoNascita);
       return maggiorenne(localDate, LocalDate.now());
    }

    static boolean maggiorenne(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            if  (Period.between(birthDate, currentDate).getYears() >= 18)
                return true;
            else    return false;
        }
        return false;
    }


}
