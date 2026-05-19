package src.com.tierpark.tier.tiere;

import src.com.tierpark.tier.Tier;

public class Fennek extends Tier {

    /**
     * Erstellt ein neues Fennek-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Fennek(String name, int alter, int futtermenge ) {
        super(name, alter, "Fennek", "Fleisch", futtermenge, "Savanne");
    }
}