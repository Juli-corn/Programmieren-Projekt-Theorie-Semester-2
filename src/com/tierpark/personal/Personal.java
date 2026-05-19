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

    public Personal(String Name, String job, String schicht, int maxAufgabenProSchicht) {
        main.id++;
        this.Name = Name;
        this.id = main.id;
        this.job = job;
        this.schicht = schicht;
        this.maxAufgabenProSchicht = maxAufgabenProSchicht;
        this.aufgabenErledigt = 0;
    }

    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getSchicht() {
        return schicht;
    }

    public int getMaxAufgabenProSchicht() {
        return maxAufgabenProSchicht;
    }

    public int getAufgabenErledigt() {
        return aufgabenErledigt;
    }

    public String getVerfuegbarkeit() {
        if (!isInSchicht()) {
            return "außerhalb der Schicht";
        }
        if (!hatKapazitaet()) {
            return "keine Kapazität mehr";
        }
        return "verfügbar";
    }

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

    public boolean hatKapazitaet() {
        return aufgabenErledigt < maxAufgabenProSchicht;
    }

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

    public void resetSchicht() {
        aufgabenErledigt = 0;
    }
}
