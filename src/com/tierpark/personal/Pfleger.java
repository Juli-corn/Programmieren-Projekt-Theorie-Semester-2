package personal;

import gehege.Gehege;

public class Pfleger extends Personal {

    private int gehegeproschicht = 2;
    private int gefütterteGehege = 0;

    /**
     * Erstellt einen neuen Pfleger.
     *
     * @param Name Name des Pflegers
     */
    public Pfleger(String Name) {
        super(Name, "Pfleger");
    }

    /**
     * Füttert die Tiere eines Geheges.
     *
     * @param gehege betroffenes Gehege
     * @return Statusmeldung der Fütterung
     */
    public String füttern(Gehege gehege) {

        if(gehegeproschicht == gefütterteGehege) {
            return "Der Pfleger " + this.getName() + " hat bereits die maximale Anzahl an Gehegen für diese Schicht gefüttert.";
        }else if(gehege.getHeuteGefüttert() == false) {
            gehege.setHeuteGefüttert(true);
            gefütterteGehege++;
            return "Der Pfleger " + this.getName() + " hat die Tiere im Gehege " + gehege.getId() + " gefüttert.";
        } else {
            return "Die Tiere im Gehege " + gehege.getId() + " wurden bereits heute gefüttert.";
        }
    }
}     
