package src.com.tierpark.personal;

import src.com.tierpark.app.main;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 abstract oder interface
    Attribute:
        - id 
        - Namen 

    Werter:
        - Schichten
        - zahl an Gehegen pro Schicht

*/

public abstract class Personal {

    private String Name;
    private int id;
    private String job;
    private String schicht;
    private int maxAufgabenProSchicht;
    private int aufgabenErledigt;

    /**
     * Erstellt ein neues Personalobjekt.
     *
     * @param Name Name des Mitarbeiters
     * @param job Berufsbezeichnung
     */
    public Personal(String Name, String job, String schicht, int maxAufgabenProSchicht) {
        main.id++;
        this.Name = Name;
        this.id = main.id;
        this.job = job;
        this.schicht = schicht;
        this.maxAufgabenProSchicht = maxAufgabenProSchicht;
        this.aufgabenErledigt = 0;
    }

    /**
     * Gibt den Namen des Mitarbeiters zurück.
     *
     * @return Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Gibt die Id des Mitarbeiters zurück.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt den Job des Mitarbeiters zurück.
     *
     * @return job
     */
    public String getJob() {
        return job;
    }

    /**
     * Gibt die Schicht des Mitarbeiters zurück.
     *
     * @return schicht
     */
    public String getSchicht() {
        return schicht;
    }

    /**
     * Gibt die maximalen Aufgaben pro Schicht zurück
     *
     * @return maxAufgabenProSchicht
     */
    public int getMaxAufgabenProSchicht() {
        return maxAufgabenProSchicht;
    }

    /**
     * Gibt die Anzahl der erledigten Aufgaben zurück.
     *
     * @return aufgabenErledigt
     */
    public int getAufgabenErledigt() {
        return aufgabenErledigt;
    }

    /**
     * Gibt zurück, ob ein Mitarbeiter verfügbar ist
     *
     * @return Ergebnis
     */
    public String getVerfuegbarkeit() {
        if (!isInSchicht()) {
            return "außerhalb der Schicht";
        }
        if (!hatKapazitaet()) {
            return "keine Kapazität mehr";
        }
        return "verfügbar";
    }

    /**
     * Gibt zurück, ob ein Mitarbeiter in seiner Schicht ist
     *
     * 7.1.1 Exceptions in Java. Java ist auch eine Insel JavaBuchFrame.PDF https://www.raffia.ch/content/docplus/Java%20ist%20auch%20eine%20Insel%20-%20Ullenboom%202001.pdf
     *
     * @return Ergebnis
     */
    public boolean isInSchicht() {
        if (schicht == null || schicht.isBlank()) {
            return false;
        }

        try {
            String[] parts = schicht.split("-");
            if (parts.length != 2) {
                return false;
            }
            LocalTime start = LocalTime.parse(parts[0].trim(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime end = LocalTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime now = LocalTime.now();

            if (end.isAfter(start)) {
                return !now.isBefore(start) && !now.isAfter(end);
            }
            return !now.isBefore(start) || !now.isAfter(end);
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    /**
     * Gibt zurück, ob ein Mitarbeiter noch Kapazitäten hat
     *
     * @return true falls hatKapazitaet, sonst false
     */
    public boolean hatKapazitaet() {
        return aufgabenErledigt < maxAufgabenProSchicht;
    }

    /**
     * Gibt zurück, ob ein Mitarbeiter verfügbar ist.
     *
     * @return true falls isVerfuegbar, sonst false
     */
    public boolean isVerfuegbar() {
        return isInSchicht() && hatKapazitaet();
    }

    public boolean assignAufgabe() {
        if (!isInSchicht() || !hatKapazitaet()) {
            return false;
        }
        aufgabenErledigt++;
        return true;
    }

    /**
     * Setzt die Schicht des Mitarbeiters zurück
     */
    public void resetSchicht() {
        aufgabenErledigt = 0;
    }
}
