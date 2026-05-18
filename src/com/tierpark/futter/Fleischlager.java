package futter;

public class Fleischlager extends Futterlager {

    /**
     * Erstellt ein neues Fleischlager-Objekt.
     *
     * @param id id des Lagers
     * @param name Name des Lagers
     * @param maxFutter maximales Futter des Lagers
     */
    public Fleischlager(int id, String name, int maxFutter ) {
        super(id, name, maxFutter, "Fleisch" );
    }
    
}
