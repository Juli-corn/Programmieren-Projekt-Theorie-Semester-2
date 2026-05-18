package futter;

public class Pflanzenlager extends Futterlager {

    /**
     * Erstellt ein neues Pflanzenlager-Objekt.
     *
     * @param id id des Lagers
     * @param name Name des Lagers
     * @param maxFutter maximales Futter des Lagers
     */
    public Pflanzenlager(int id, String name, int maxFutter ) {
        super(id, name, maxFutter, "Pflanzen");
    }
}