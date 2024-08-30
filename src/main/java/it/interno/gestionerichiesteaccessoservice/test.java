package it.interno.gestionerichiesteaccessoservice;

import it.interno.gestionerichiesteaccessoservice.exceptions.CodiceFiscaleNonValidoException;
import it.interno.gestionerichiesteaccessoservice.utils.GenericUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;

/**
 * @author mirco.cennamo on 27/08/2024
 * @project gestione-richiesteaccesso-service-master
 */
public class test {

    public static void main(String[] args) {
       String codiceFiscale = "MRDFNC22A41C352W";
        if(StringUtils.isBlank(codiceFiscale))
            throw new CodiceFiscaleNonValidoException("Il codice fiscale inserito non è valido.");
        if(!GenericUtils.isMaggiorenne(codiceFiscale))
            throw new CodiceFiscaleNonValidoException("Il codice fiscale corrisponde ad un minore.");
        else{
            System.out.println("Codice fiscale maggiorenne");
        }
    }

}
