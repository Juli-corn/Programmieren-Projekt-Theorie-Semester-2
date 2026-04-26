package tierpark.personal;

public class Pfleger extends Personal {
    private String spezialisierung = "Pfleger";

    public Pfleger(String Name) {
        super(Name);
    }

    public String getSpezialisierung() {
        return spezialisierung;
    }
     
}
