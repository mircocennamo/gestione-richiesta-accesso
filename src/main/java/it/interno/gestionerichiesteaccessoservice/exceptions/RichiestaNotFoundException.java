package it.interno.gestionerichiesteaccessoservice.exceptions;

public class RichiestaNotFoundException extends RuntimeException{
    public RichiestaNotFoundException() {
        super("La richiesta non è stata trovata.");
    }
}
