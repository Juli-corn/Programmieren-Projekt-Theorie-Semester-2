package tier.tiere;

import tier.Tier;

public class Capybara extends Tier {

    /**
     * Erstellt ein neues Capybara-Objekt.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param futtermenge tägliche Futtermenge
     */
    public Capybara(String name, int alter, int futtermenge ) {
        super(name, alter, "Capybara", "Pflanzen", futtermenge, "Dschungelgehege" );
    }
    
}
