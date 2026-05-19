package src.com.tierpark.tier.tiere;

import src.com.tierpark.tier.Tier;

public class Axolotl extends Tier {

    /**
     * Erstellt ein neues Axolotl-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Axolotl(String name, int alter, int futtermenge ) {
        super(name, alter, "Axolotl", "Fleisch", futtermenge, "Wassergehege");
    }
    
}
