package src.com.tierpark.tier.tiere;

import src.com.tierpark.tier.Tier;

public class Braunbaer extends Tier {

    /**
     * Erstellt ein neues Braunbaer-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Braunbaer(String name, int alter, int futtermenge ) {
        super(name, alter, "Braunbär", "Pflanzen", futtermenge, "Waldgehege" );
    }
    
}
