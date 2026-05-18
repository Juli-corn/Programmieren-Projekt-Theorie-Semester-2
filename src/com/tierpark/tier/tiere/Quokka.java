package tier.tiere;

import tier.Tier;

public class Quokka extends Tier {

    /**
     * Erstellt ein neues Quokka-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Quokka(String name, int alter, int futtermenge ) {
        super(name, alter, "Quokka", "Pflanzen", futtermenge, "Savanne" );
    }
    
}
