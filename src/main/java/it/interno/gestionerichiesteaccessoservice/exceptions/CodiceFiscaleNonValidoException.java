package it.interno.gestionerichiesteaccessoservice.exceptions;

/**
 * @author mirco.cennamo on 27/08/2024
 * @project gestione-richiesteaccesso-service-master
 */
public class CodiceFiscaleNonValidoException extends RuntimeException{

    public CodiceFiscaleNonValidoException(String s) {
        super(s);
    }
}
