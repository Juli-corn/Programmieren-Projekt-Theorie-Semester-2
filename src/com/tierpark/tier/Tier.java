package tierpark.tier;
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

import tierpark.gehege.Gehege;

public abstract class Tier {
    protected int tId;
    protected String name;
    protected int alter;
    protected String art;
    protected boolean istKrank;
    protected String lieblingsfutter;
    protected Gehege gehege;
    protected int futtermenge;     // Verbrauch kg/Tag

    public Tier(int id, String name, int alter, String art, String lieblingsfutter, int futtermenge) {
        this.tId = id;
        this.name = name;
        this.alter = alter;
        this.art = art;
        this.lieblingsfutter = lieblingsfutter;
        this.istKrank = false;
        this.futtermenge = futtermenge;
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

    public Gehege getGehege() {
        return gehege;
    }

    public void setGehege(Gehege gehege) {
        this.gehege = gehege;
    }

    public int getFuttermenge() {
        return futtermenge;
    }

};