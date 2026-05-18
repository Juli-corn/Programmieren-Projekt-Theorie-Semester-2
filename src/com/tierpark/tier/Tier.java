package tier;

import app.main;
/* -> abstract
    Attribute:
        - int id
        - string Art
        - string Name
        - int Alter
        - boolean Krank
        - Futter Lieblingsfutter 
        - gehege Gehege
 */ 

public abstract class Tier {
    protected int tId;
    protected String name;
    protected int alter;
    protected String art;
    protected boolean istKrank;
    protected String lieblingsfutter;
    protected int futtermenge;     // Verbrauch kg/Tag
    protected String gehegetyp;
    protected boolean inGehege;

    /**
     * Erstellt ein neues Tier mit allen Eigenschaften.
     *
     * @param name Name des Tieres
     * @param alter Alter des Tieres
     * @param art Art des Tieres
     * @param lieblingsfutter bevorzugtes Futter
     * @param futtermenge benötigte Futtermenge
     * @param gehegetyp benötigter Gehegetyp
     */
    public Tier(String name, int alter, String art, String lieblingsfutter, int futtermenge, String gehegetyp) {
        main.id++;
        this.tId = main.id;
        this.name = name;
        this.alter = alter;
        this.art = art;
        this.lieblingsfutter = lieblingsfutter;
        this.istKrank = false;
        this.futtermenge = futtermenge;
        this.gehegetyp = gehegetyp;
        this.inGehege = false;
    }

    /**
     * Gibt die ID des Tieres zurück.
     *
     * @return tId
     */
    public int getId() {
        return tId;
    }

    /**
     * Gibt den Namen des Tieres zurück.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt das Alter des Tieres zurück.
     *
     * @return alter
     */
    public int getAlter() {
        return alter;
    }

    /**
     * Gibt die Art des Tieres zurück.
     *
     * @return art
     */
    public String getArt() {
        return art;
    }

    /**
     * Setzt den Gesundheitszustand des Tieres.
     *
     * @param istKrank Gesundheitsstatus
     */
    public void setIstKrank(boolean istKrank) {
        this.istKrank = istKrank;
        
    }

    /**
     * Prüft, ob das Tier krank ist.
     *
     * @return true falls krank, sonst false
     */
    public boolean getIstKrank() {
        return istKrank;
    }

    /**
     * Prüft das Lieblingsfutter vom Tier.
     *
     * @return lieblingsfutter
     */
    public String getLieblingsfutter() {
        return lieblingsfutter;
    }

    /**
     * Gibt die Futtermenge zurück.
     *
     * @return futtermenge
     */
    public int getFuttermenge() {
        return futtermenge;
    }

    /**
     * Gibt den GehegeTyp zurück
     *
     * @return gehegetyp
     */
    public String getGehegeTyp(){
        return gehegetyp;
    }

    /**
     * Gibt zurück ob das Tier im Gehege ist.
     *
     * @return true falls inGehege, sonst false
     */
    public boolean getInGehege(){
        return inGehege;
    }

    /**
     * Gibt zurück ob das Tier im Gehege ist.
     *
     * @return true falls inGehege, sonst false
     */
    public void setInGehege(boolean inGehege) {
        this.inGehege = inGehege;
    }

}