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


    public int getId() {
        return tId;
    }

    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    public String getArt() {
        return art;
    }

    public void setIstKrank(boolean istKrank) {
        this.istKrank = istKrank;
        
    }

    public boolean getIstKrank() {
        return istKrank;
    }

    public String getLieblingsfutter() {
        return lieblingsfutter;
    }

    public int getFuttermenge() {
        return futtermenge;
    }


    public String getGehegeTyp(){
        return gehegetyp;
    }

    public boolean getInGehege(){
        return inGehege;
    }

    public void setInGehege(boolean inGehege) {
        this.inGehege = inGehege;
    }

};