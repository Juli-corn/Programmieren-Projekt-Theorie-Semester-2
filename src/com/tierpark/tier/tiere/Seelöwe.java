package src.com.tierpark.tier.tiere;

import src.com.tierpark.tier.Tier;

public class Seelöwe extends Tier {

    /**
     * Erstellt ein neues Seelöwe-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Seelöwe(String name, int alter, int futtermenge ) {
        super(name, alter, "Seelöwe", "Fisch", futtermenge, "Eisgehege" );
    }
    
}
