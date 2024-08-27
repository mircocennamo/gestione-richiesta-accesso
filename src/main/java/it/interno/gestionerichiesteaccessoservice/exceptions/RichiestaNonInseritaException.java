package it.interno.gestionerichiesteaccessoservice.exceptions;

public class RichiestaNonInseritaException extends RuntimeException{
    public RichiestaNonInseritaException() {
        super("Errore durante l'inserimento.");
    }
}
