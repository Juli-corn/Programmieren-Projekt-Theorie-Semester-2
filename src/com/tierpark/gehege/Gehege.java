package gehege;
import app.main;
import tier.Tier;

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


public abstract class Gehege{

    private int id;
    private int maxTiere;
    private int anzahlUntergebrachteTiere;
    private String fuetterungszeit;
    private String typ;
    private Tier[] tier = new Tier[maxTiere];
    private int[] verbrauch = new int[3];
    private boolean heuteGefüttert;

    public Gehege(int maxTiere, String fuetterungszeit, String typ) {
        
        main.id++;
        this.id = main.id;
        this.maxTiere = maxTiere;
        this.anzahlUntergebrachteTiere = 0;
        this.fuetterungszeit = fuetterungszeit;
        this.typ = typ;
        this.tier = new Tier[maxTiere];
        heuteGefüttert = false;
    }

    public String tierHinzufuegen(Tier tier) {

        if(tier.getGehegeTyp().equals(this.typ)){

            if (anzahlUntergebrachteTiere < maxTiere ) {

                this.tier[anzahlUntergebrachteTiere] = tier;
                anzahlUntergebrachteTiere++;
                futterverbrauch();
                return tier.getClass().getSimpleName() + " " + tier.getName() + " wurde erfolgreich hinzugefügt.";

            } else {

                return "Das Gehege ist voll. Es können keine weiteren Tiere hinzugefügt werden.";
            }

        } else {

            return "Das Tier " + tier.getName() + " gehört nicht in das Gehege " + this.typ + ".";
        }
    }

    public String tierEntfernen(Tier tier) {

        for (int i = 0; i < anzahlUntergebrachteTiere; i++) {
            if (this.tier[i] == tier) {
                this.tier[i] = null;

                for(int j = 0; j < anzahlUntergebrachteTiere - 1; j++) {
                    if(this.tier[j] == null) {
                        this.tier[j] = this.tier[j + 1];
                        this.tier[j + 1] = null;
                    }
                }
                anzahlUntergebrachteTiere--;
                futterverbrauch();
                return tier.getClass().getSimpleName() + " " + tier.getName() + " wurde erfolgreich entfernt.";
            }
        }
        return "Das Tier " + tier.getName() + " ist nicht in diesem Gehege.";
    }

    public int getId() {
        return id;
    }

    public int getMaxTiere() {
        return maxTiere;
    }

    public void setMaxTiere(int newMax) {
        this.maxTiere = newMax;
    }

    public int getAnzahlUntergebrachteTiere() {
        return anzahlUntergebrachteTiere;
    }

    public String getTierNamen() {
        String tierNamen = "";
        for (int i = 0; i < anzahlUntergebrachteTiere; i++) {
            if (this.tier[i] != null) {
                tierNamen = tierNamen + this.tier[i].getName() + " (" + this.tier[i].getClass().getSimpleName() + "), ";
            }
        }
        return tierNamen;
    }

    public void setFuetterungszeit(String newF) {
        this.fuetterungszeit = newF;
    }
  
    public String getFuetterungszeit() {
        return fuetterungszeit;
    }

    public String getTyp() {
        return typ;
    }

    public void futterverbrauch() {

        int fischfutterverbrauch = 0;
        int fleischfutterverbrauch = 0;
        int pflanzenfutterverbrauch = 0;

        for (int i = 0; i < tier.length; i++) {

            if (tier[i] == null) continue;

            String futter = tier[i].getLieblingsfutter();
            if ("Fischfutter".equalsIgnoreCase(futter) || "Fisch".equalsIgnoreCase(futter)) {
                fischfutterverbrauch += tier[i].getFuttermenge();
            } else if ("Fleischfutter".equalsIgnoreCase(futter) || "Fleisch".equalsIgnoreCase(futter)) {
                fleischfutterverbrauch += tier[i].getFuttermenge();
            } else if ("Pflanzenfutter".equalsIgnoreCase(futter) || "Pflanzen".equalsIgnoreCase(futter)) {
                pflanzenfutterverbrauch += tier[i].getFuttermenge();
            }
        }

        verbrauch[0] = fischfutterverbrauch;
        verbrauch[1] = fleischfutterverbrauch;
        verbrauch[2] = pflanzenfutterverbrauch;
        
    }

    public int[] getFutterverbrauch() {
        return verbrauch;
    }

    public Tier[] getTiere() {
        return tier;
    }

    public boolean getHeuteGefüttert() {
        return heuteGefüttert;
    }

    public void setHeuteGefüttert(boolean heuteGefüttert) {
        this.heuteGefüttert = heuteGefüttert;
    }

}