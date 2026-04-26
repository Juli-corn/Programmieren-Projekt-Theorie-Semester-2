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
    protected int maxFutter;        // Kg
    protected int minFutter;        // Kg | Wert, nach dessen Unterschreitung nachbestellt wird. (evtl. durch Funktion bestimmen -> ges. Summe Futterart pro Tag als min Wert)

    public Futterlager(int id,String name, String futterart, int maxFutter) {
        this.fId = id;
        this.name = name;
        this.futterart = futterart;
        this.maxFutter = maxFutter;
        this.minFutter = 0;
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

    public void setMinFutter() {        // Hilfsvariable für ges Futtermenge je Futterart.

    }
}