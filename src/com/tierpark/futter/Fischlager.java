package src.com.tierpark.futter;

public class Fischlager extends Futterlager {

    /**
     * Erstellt ein neues Fischlager-Objekt.
     *
     * @param id id des Lagers
     * @param name Name des Lagers
     * @param maxFutter maximales Futter des Lagers
     */
    public Fischlager(int id, String name, int maxFutter ) {
        super(id, name, maxFutter, "Fisch");
    }
    
}
