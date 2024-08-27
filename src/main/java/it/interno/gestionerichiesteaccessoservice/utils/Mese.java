package it.interno.gestionerichiesteaccessoservice.utils;

/**
 * @author mirco.cennamo on 27/08/2024
 *
 */
public enum Mese {
    A (1),
    B(2),
    C(3),
    D(4),
    E(5),
    H(6),
    L(7),
    M(8),
    P(9),
    R(10),
    S(11),
    T(12);


    private Integer mese;

    private Mese(int mese) {
        this.mese = mese;
    }

    public static Mese fromValue(String value) {
        for (Mese mese : Mese.values()) {
            if (mese.name().equals(value)) {
                return mese;
            }
        }
        return null;
    }

    public Integer getMese()
    {
        return this.mese;
    }

}
