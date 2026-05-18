package tier.tiere;

import tier.Tier;

public class Krokodil extends Tier {

    /**
     * Erstellt ein neues Krokodil-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Krokodil(String name, int alter, int futtermenge ) {
        super(name, alter, "Krokodil", "Fleisch", futtermenge, "Dschungelgehege" );
    }
    
}
