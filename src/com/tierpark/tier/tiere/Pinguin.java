package tier.tiere;

import tier.Tier;


public class Pinguin extends Tier {

    /**
     * Erstellt ein neues Pinguin-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Pinguin(String name, int alter, int futtermenge ) {
        super(name, alter, "Pinguin", "Fisch", futtermenge, "Eisgehege" );
    }

}