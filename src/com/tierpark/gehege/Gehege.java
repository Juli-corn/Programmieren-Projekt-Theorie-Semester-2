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

    /**
     * Erstellt ein neues Gehege.
     *
     * @param maxTiere maximale Tiere des Geheges
     * @param fuetterungszeit Fuetterungszeit des Geheges
     * @param typ Gehegetyp
     */
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

    /**
     * Fügt ein Tier zum Gehege hinzu.
     *
     * @param tier hinzuzufuegendes Tier
     * @return Ergebnis der Aktion
     */
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

    /**
     * Entfernt ein Tier aus dem Gehege.
     *
     * @param tier zu entfernendes Tier
     * @return Ergebnis der Aktion
     */
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

    /**
     * Gibt Id zurück.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt MaxTiere zurück.
     *
     * @return maxTiere
     */
    public int getMaxTiere() {
        return maxTiere;
    }

    /**
     * Setzt die maximale Anzahl an Tieren.
     *
     * @param newMax die neue maximale Anzahl an Tieren
     */
    public void setMaxTiere(int newMax) {
        this.maxTiere = newMax;
    }

    /**
     * Gibt die Anzahlt untergebrachter Tiere zurück.
     *
     * @return anzahlUntergebrachteTiere
     */
    public int getAnzahlUntergebrachteTiere() {
        return anzahlUntergebrachteTiere;
    }

    /**
     * Gibt TierNamen zurück.
     *
     * @return tierNamen
     */
    public String getTierNamen() {
        String tierNamen = "";
        for (int i = 0; i < anzahlUntergebrachteTiere; i++) {
            if (this.tier[i] != null) {
                tierNamen = tierNamen + this.tier[i].getName() + " (" + this.tier[i].getClass().getSimpleName() + "), ";
            }
        }
        return tierNamen;
    }

    /**
     * Gibt AnzahlUntergebrachterTiere zurück.
     *
     * @return anzahlUntergebrachterTiere
     */
    public int getAnzahlUntergebrachteTiere() {
        return anzahlUntergebrachteTiere;
    }

    /**
     * Gibt TierNamen zurück.
     *
     * @return tierNamen
     */
    public String getTierNamen() {
        String tierNamen = "";
        for (int i = 0; i < anzahlUntergebrachteTiere; i++) {
            if (this.tier[i] != null) {
                tierNamen = tierNamen + this.tier[i].getName() + " (" + this.tier[i].getClass().getSimpleName() + "), ";
            }
        }
        return tierNamen;
    }

    /**
     * Gibt TierNamen zurück.
     *
     * @return tierNamen
     */
    public String getTierNamen() {
        String tierNamen = "";
        for (int i = 0; i < anzahlUntergebrachteTiere; i++) {
            if (this.tier[i] != null) {
                tierNamen = tierNamen + this.tier[i].getName() + " (" + this.tier[i].getClass().getSimpleName() + "), ";
            }
        }
        return tierNamen;
    }

    /**
     * Setzt die Fuetterungszeite
     *
     * @param newF die neue fuetterungszeit
     */
    public void setFuetterungszeit(String newF) {
        this.fuetterungszeit = newF;
    }

    /**
     * Gibt Fuetterungszeit zurück.
     *
     * @return futternungszeit
     */
    public String getFuetterungszeit() {
        return fuetterungszeit;
    }

    /**
     * Gibt Typ zurück.
     *
     * @return typ
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Setzt das Rechnungs-Arrys mit seinen drei Possitionen auf die verschiedenen Futterarten.
     */
    public void futterverbrauch() {

        int fischfutterverbrauch = 0;
        int fleischfutterverbrauch = 0;
        int pflanzenfutterverbrauch = 0;

        for (int i = 0; i < tier.length; i++) {

            if (tier[i] == null) continue;

            if (tier[i].getLieblingsfutter().equals("Fischfutter")) {
                fischfutterverbrauch = fischfutterverbrauch + tier[i].getFuttermenge();

            } else if (tier[i].getLieblingsfutter().equals("Fleischfutter")) {
                fleischfutterverbrauch = fleischfutterverbrauch + tier[i].getFuttermenge();

            } else if (tier[i].getLieblingsfutter().equals("Pflanzenfutter")) {
                pflanzenfutterverbrauch = pflanzenfutterverbrauch + tier[i].getFuttermenge();
            }
        }

        verbrauch[0] = fischfutterverbrauch;
        verbrauch[1] = fleischfutterverbrauch;
        verbrauch[2] = pflanzenfutterverbrauch;
        
    }

    /**
     * Gibt Futterverbrauch zurück.
     *
     * @return verbrauch
     */
    public int[] getFutterverbrauch() {
        return verbrauch;
    }

    /**
     * Gibt Tiere zurück.
     *
     * @return tier
     */
    public Tier[] getTiere() {
        return tier;
    }

    /**
     * Gibt Heute Gefüttert zurück.
     *
     * @return true falls heuteGefüttert, sonst false
     */
    public boolean getHeuteGefüttert() {
        return heuteGefüttert;
    }

    /**
     * Setzt heuteGefüttert auf true oder false
     *
     * @param heuteGefüttert
     */
    public void setHeuteGefüttert(boolean heuteGefüttert) {
        this.heuteGefüttert = heuteGefüttert;
    }

}