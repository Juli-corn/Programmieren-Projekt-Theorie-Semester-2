package tierpark.personal;

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

    public Personal(String Name) {
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

    


}