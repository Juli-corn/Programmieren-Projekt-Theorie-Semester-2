package tier.tiere;

import tier.Tier;

public class Erdmännchen extends Tier {

    /**
     * Erstellt ein neues Erdmännchen-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Erdmännchen(String name, int alter, int futtermenge ) {
        super(name, alter, "Erdmännchen", "Fleisch", futtermenge, "Savanne" );
    }
    
}
