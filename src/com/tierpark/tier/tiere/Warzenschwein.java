package src.com.tierpark.tier.tiere;

import src.com.tierpark.tier.Tier;

public class Warzenschwein extends Tier {

    /**
     * Erstellt ein neues Warzenschwein-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Warzenschwein(String name, int alter, int futtermenge ) {
        super(name, alter, "Warzenschwein", "Pflanzen", futtermenge, "Savanne" );
    }
    
}
