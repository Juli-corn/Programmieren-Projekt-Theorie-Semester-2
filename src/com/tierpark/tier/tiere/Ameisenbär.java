package tier.tiere;

import tier.Tier;

public class Ameisenbär extends Tier {

    /**
     * Erstellt ein neues Ameisenbär-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Ameisenbär(String name, int alter, int futtermenge ) {
        super(name, alter, "Ameisenbär", "Fleisch", futtermenge, "Savanne");
    }
    
}
