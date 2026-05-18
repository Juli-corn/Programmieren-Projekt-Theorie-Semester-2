package tier.tiere;

import tier.Tier;

public class Nacktmull extends Tier {

    /**
     * Erstellt ein neues Nacktmull-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Nacktmull(String name, int alter, int futtermenge ) {
        super(name, alter, "Nacktmull", "Pflanzen", futtermenge, "Savanne" );
    }
    
}
