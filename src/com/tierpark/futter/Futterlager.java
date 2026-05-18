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
        this.futter = maxFutter;
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