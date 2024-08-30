package it.interno.gestionerichiesteaccessoservice.enumeration;

/**
 * @author mirco.cennamo on 30/08/2024
 * @project portale-gestionerichiestaaccessoservice
 */
public enum Ripartizione {
    PIEMONTE("01","NORD-OVEST"),
    VALLE_DAOSTA ("02","NORD-OVEST"),
    LOMBARDIA ("03","NORD-OVEST"),
    TRENTINO_ALTO_ADIGE("04","NORD-EST"),
    VENETO("05","NORD-EST"),
    FRIULI_VENEZIA_GIULIA("06","NORD-EST"),

    LIGURIA ("07","NORD-OVEST"),
    EMILIA_ROMAGNA("08","NORD-EST"),
    TOSCANA("09","CENTRO"),

    UMBRIA("10","CENTRO"),

    MARCHE("11","CENTRO"),

    LAZIO("12","CENTRO"),
    ABRUZZO("13","CENTRO"),
    MOLISE("14","CENTRO"),
    CAMPANIA("15","SUD"),
    PUGLIA("16","SUD"),
    BASILICATA("17","SUD"),
    CALABRIA("18","SUD"),
    SICILIA("19","ISOLE"),
    SARDEGNA("20","ISOLE");










    private String codiceRegione;
    private String ripartizione;

    private Ripartizione(String codiceRegione,String ripartizione) {
        this.codiceRegione = codiceRegione;
        this.ripartizione = ripartizione;
    }

    public static Ripartizione fromCodiceRegione(String codiceRegione) {
        for (Ripartizione ripartizione : Ripartizione.values()) {
            if (ripartizione.getCodiceRegione().equals(codiceRegione)) {
                return ripartizione;
            }
        }
        return null;
    }

    public String getRipartizione()
    {
        return this.ripartizione;
    }

    public String getCodiceRegione()
    {
        return this.codiceRegione;
    }

}
