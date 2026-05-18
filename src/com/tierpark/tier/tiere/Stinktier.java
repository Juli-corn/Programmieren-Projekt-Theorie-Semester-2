package tier.tiere;

import tier.Tier;

public class Stinktier extends Tier {

    /**
     * Erstellt ein neues Stinktier-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Stinktier(String name, int alter, int futtermenge ) {
        super(name, alter, "Stinkier", "Fleisch", futtermenge, "Savanne" );
    }
    
}
