package personal;

import tier.Tier;

public class Tierarzt extends Personal {


    public Tierarzt(String Name) {
        super(Name, "Tierarzt");
    }

    public String heileTier(Tier tier) {

        if(tier.istKrank() == true) {
            tier.setKrank(false);
            return "Der Tierarzt " + this.getName() + " hat das Tier geheilt.";
        }else{
            return "Das Tier ist nicht krank, es muss nicht geheilt werden.";
        }

        
    }
    
}
