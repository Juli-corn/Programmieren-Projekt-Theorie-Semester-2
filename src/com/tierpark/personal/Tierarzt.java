package personal;

import tier.Tier;

public class Tierarzt extends Personal {

    public Tierarzt(String Name) {
        this(Name, "10:00-18:00", 2);
    }

    public Tierarzt(String Name, String schicht, int maxTiereProSchicht) {
        super(Name, "Tierarzt", schicht, maxTiereProSchicht);
    }

    public String heileTier(Tier tier) {
        if (!isInSchicht()) {
            return "Der Tierarzt " + this.getName() + " ist außerhalb seiner Schicht und kann nicht eingesetzt werden.";
        }

        if (!hatKapazitaet()) {
            return "Der Tierarzt " + this.getName() + " hat bereits die maximale Anzahl an Tieren in dieser Schicht betreut.";
        }

        if (!tier.getIstKrank()) {
            return "Das Tier " + tier.getName() + " ist nicht krank und muss nicht geheilt werden.";
        }

        assignAufgabe();
        tier.setIstKrank(false);
        return "Der Tierarzt " + this.getName() + " hat das Tier " + tier.getName() + " geheilt.";
    }
}

