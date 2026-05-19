package src.com.tierpark.personal;

import src.com.tierpark.gehege.*;

public class Pfleger extends Personal {

    private int gehegeproschicht = 2;
    private int gefütterteGehege = 0;

    /**
     * Erstellt einen neuen Pfleger.
     *
     * @param Name Name des Pflegers
     */
    public Pfleger(String Name) {
        this(Name, "08:00-16:00", 2);
    }

    public Pfleger(String Name, String schicht, int gehegeproSchicht) {
        super(Name, "Pfleger", schicht, gehegeproSchicht);
    }

    /**
     * Füttert die Tiere eines Geheges.
     *
     * @param gehege betroffenes Gehege
     * @return Statusmeldung der Fütterung
     */
    public String füttern(Gehege gehege) {
        if (!isInSchicht()) {
            return "Der Pfleger " + this.getName() + " ist außerhalb seiner Schicht und kann nicht eingesetzt werden.";
        }

        if (!hatKapazitaet()) {
            return "Der Pfleger " + this.getName() + " hat bereits die maximale Anzahl an Gehegen für diese Schicht bearbeitet.";
        }

        if (gehege.getHeuteGefüttert()) {
            return "Die Tiere im Gehege " + gehege.getId() + " wurden bereits heute gefüttert.";
        }

        gehege.setHeuteGefüttert(true);
        assignAufgabe();
        return "Der Pfleger " + this.getName() + " hat die Tiere im Gehege " + gehege.getId() + " gefüttert.";
    }
}

