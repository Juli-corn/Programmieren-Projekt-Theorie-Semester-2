package futter;
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

    public Futterlager(int id,String name, int maxFutter, String futterart) {
        this.fId = id;
        this.name = name;
        this.futterart = futterart;
        this.maxFutter = maxFutter;
        this.minFutter = 0;
        this.futter = 0;
    }

    public int getId() {
        return fId;
    }

    public String getFutterart() {
        return futterart;
    }

    public int getMaxFutter() {
        return maxFutter;
    }

    public int getMinFutter() {
        return minFutter;
    }

    public void setMinFutter() {        // Hilfsvariable für ges Futtermenge je Futterart. In GUI aktivieren für neues setzen. 

    }

    public int getFutter() {
        return futter;
    }

    public void bestellung() {
        if (futter <= minFutter){
            System.out.println("Es wurden " + (maxFutter - futter) + " Kg Futter nachbestellt." );
            futter = maxFutter;
            System.out.println("Der neue Futterbestand beträgt " + futter + " Kg.");
        };
        
    }

    public void ausgabe(int Kg) {     // zieht bei Fütterung das verbrauchte Material ab.
        try {
            if (Kg > futter) {
                throw new IllegalArgumentException("Nicht genügend Futter in Lager, minFutter checken!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Fehler bei automatischen Bestellungen: " + e.getMessage());
        };

        System.out.println("Ausgabe: " + Kg + "Kg " + futterart + ".");
        futter = futter - Kg;
    }

}