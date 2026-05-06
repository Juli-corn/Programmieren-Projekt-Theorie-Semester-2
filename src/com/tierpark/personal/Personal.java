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

    public Personal(String Name, String job) {
        main.id++;
        this.Name = Name;
        this.id = main.id;


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

}