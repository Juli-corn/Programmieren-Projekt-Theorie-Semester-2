package personal;

public class Tierarzt extends Personal {
    private String spezialisierung = "Tierarzt";

    public Tierarzt(String Name) {
        super(Name);
    }

    public String getSpezialisierung() {
        return spezialisierung;
    }
    
}
