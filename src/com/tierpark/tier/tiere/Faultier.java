package tier.tiere;

import tier.Tier;

public class Faultier extends Tier {

    /**
     * Erstellt ein neues Faultier-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Faultier(String name, int alter, int futtermenge ) {
        super(name, alter, "Faultier", "Pflanzen", futtermenge, "Dschungel" );
    }
    
}
