package src.com.tierpark.tier.tiere;

import src.com.tierpark.tier.Tier;

public class Schildkröte extends Tier {

    /**
     * Erstellt ein neues Schildkröte-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Schildkröte(String name, int alter, int futtermenge ) {
        super(name, alter, "Oliv-Bastardschildkröte", "Fisch", futtermenge, "Wassergehege" );
    }
    
}
