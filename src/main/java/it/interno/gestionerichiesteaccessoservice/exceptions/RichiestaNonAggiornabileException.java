package it.interno.gestionerichiesteaccessoservice.exceptions;

public class RichiestaNonAggiornabileException extends RuntimeException{
    public RichiestaNonAggiornabileException() {
        super("Non è possibile aggiornare la richiesta di registrazione.");
    }
}
