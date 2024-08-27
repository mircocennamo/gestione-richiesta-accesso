package it.interno.gestionerichiesteaccessoservice.exceptions;

public class UtenteNotFoundException extends RuntimeException{
    public UtenteNotFoundException() {
        super("L'utente non è presente in base dati.");
    }
}
