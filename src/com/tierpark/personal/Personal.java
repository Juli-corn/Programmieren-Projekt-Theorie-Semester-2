package personal;
import app.main;

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

    /**
     * Erstellt ein neues Personalobjekt.
     *
     * @param Name Name des Mitarbeiters
     * @param job Berufsbezeichnung
     */
    public Personal(String Name, String job) {
        main.id++;
        this.Name = Name;
        this.id = main.id;


    }

    /**
     * Gibt den Namen des Mitarbeiters zurück.
     *
     * @return Name des Mitarbeiters
     */
    public String getName() {
        return Name;
    }

    /**
     * Gibt die Id des Mitarbeiters zurück.
     *
     * @return id des Mitarbeiters
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt den Job des Mitarbeiters zurück.
     *
     * @return job des Mitarbeiters
     */
    public String getJob() {
        return job;
    }

}