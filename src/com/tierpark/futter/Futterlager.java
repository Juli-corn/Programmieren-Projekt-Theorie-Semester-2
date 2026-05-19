package src.com.tierpark.futter;
/*
    - Futterarten 
    - tätigt Bestellungen -> Gui Meldung bei unterschreitung automatisch auffüllnd
    - min Wert
    - usw.
    - Futterarten - jeweils Lagerrbestand
    - 

*/

public abstract class Futterlager {

    protected int fId;
    protected String name;
    protected String futterart;
    protected int futter;           // Kg
    protected int maxFutter;        // Kg
    protected int minFutter;        // Kg | Wert, nach dessen Unterschreitung nachbestellt wird. (evtl. durch Funktion bestimmen -> ges. Summe Futterart pro Tag als min Wert)

    /**
     * Erstellt ein neues Futterlager.
     *
     * @param id id des Lagers
     * @param name Name des Lagers
     * @param maxFutter maximales Futter des Lagers
     * @param futterart Art des Futters
     */
    public Futterlager(int id,String name, int maxFutter, String futterart) {
        this.fId = id;
        this.name = name;
        this.futterart = futterart;
        this.maxFutter = maxFutter;
        this.minFutter = 0;
        this.futter = maxFutter;
    }

    /**
     * Gibt die ID des Lagers zurück.
     *
     * @return fId
     */
    public int getId() {
        return fId;
    }

    /**
     * Gibt die Futterart zurück.
     *
     * @return futterart
     */
    public String getFutterart() {
        return futterart;
    }

    /**
     * Gibt die maximale Lagerkapazität zurück.
     *
     * @return maxFutter
     */
    public int getMaxFutter() {
        return maxFutter;
    }

    /**
     * Gibt den Mindestbestand zurück.
     *
     * @return minFutter
     */
    public int getMinFutter() {
        return minFutter;
    }

    /**
     * Setzt den Mindestbestand des Futters
     */
                                                // Hilfsvariable für ges Futtermenge je Futterart. In GUI aktivieren für neues setzen. 
    public void setMinFutter(int minFutter) {
        this.minFutter = Math.max(0, minFutter);
    }

    public void setMaxFutter(int maxFutter) {
        if (maxFutter < 0) {
            throw new IllegalArgumentException("Maximalmenge darf nicht negativ sein.");
        }
        this.maxFutter = maxFutter;
        if (this.futter > maxFutter) {
            this.futter = maxFutter;
        }
    }

    /**
     * Gibt den aktuellen Futterbestand zurück.
     *
     * @return futter
     */
    public int getFutter() {
        return futter;
    }

    /**
     * Führt eine automatische Nachbestellung aus,
     * wenn der Bestand kleiner oder gleich dem Mindestbestand ist.
     */
    public void bestellung() {
        if (futter <= minFutter){
            System.out.println("Es wurden " + (maxFutter - futter) + " Kg Futter nachbestellt." );
            futter = maxFutter;
            System.out.println("Der neue Futterbestand beträgt " + futter + " Kg.");
        };
        
    }

    /**
     * Zieht die angegebene Futtermenge vom Lagerbestand ab.
     *
     * @param Kg
     * @throws IllegalArgumentException Wenn nicht genügend Futter vorhanden ist
     */
                                    // zieht bei Fütterung das verbrauchte Material ab.
        try {
    public String ausgabe(int Kg) {
        if (Kg < 0) {
            return "Ungültige Futtermenge.";
        }
        if (Kg > futter) {
            bestellung();
            if (Kg > futter) {
                return "Nicht genügend Futter im Lager, auch nach Nachbestellung nicht verfügbar.";
            }
        }

        futter -= Kg;
        StringBuilder message = new StringBuilder("Ausgabe: " + Kg + "Kg " + futterart + ".");

        if (futter < minFutter) {
            bestellung();
            message.append(" Mindestbestand unterschritten, Lager aufgefüllt auf ").append(futter).append(" Kg.");
        }

        return message.toString();
    }

}