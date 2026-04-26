package gehege;

/*
-> abstract oder Interface 
    Attribute:
        - id
        - string Typ
        - int max Tiere
        - Untergebrachte Tiere 
        - Fütterungszeit
        - Futterverbrauch 

*/


abstract class Gehege{
    private int id;
    private int maxTiere;
    private int untergebrachteTiere;
    private String fuetterungszeit;
    private int futterverbrauch;
    private String typ;

    public Gehege(int maxTiere, String fuetterungszeit) {
        main.id++;
        this.id = main.id;
        this.maxTiere = maxTiere;
        this.untergebrachteTiere = 0;
        this.fuetterungszeit = fuetterungszeit;
        this.futterverbrauch = 0;
    }

    public int getId() {
        return id;
    }

    public int getMaxTiere() {
        return maxTiere;
    }

    public int getUntergebrachteTiere() {
        return untergebrachteTiere;
    }

    public String getFuetterungszeit() {
        return fuetterungszeit;
    }

    public int getFutterverbrauch() {
        return futterverbrauch;
    }

    public String getTyp() {
        return typ;
    }

    public int FutterverbrauchBerechnen() {
        int futterverbrauch = getUntergebrachteTiere() * 5;
        return futterverbrauch;
    }
}