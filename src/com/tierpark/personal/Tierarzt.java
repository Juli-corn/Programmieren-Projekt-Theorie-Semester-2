package personal;

import tier.Tier;

public class Tierarzt extends Personal {

    /**
     * Erstellt einen neuen Tierarzt.
     *
     * @param Name Name des Tierarztes.
     */
    public Tierarzt(String Name) {
        super(Name, "Tierarzt");
    }

    /**
     * Behandelt ein krankes Tier.
     *
     * @param tier zu behandelndes Tier
     * @return Ergebnis der Behandlung
     */
    public String heileTier(Tier tier) {

        if(tier.getIstKrank() == true) {
            tier.setIstKrank(false);
            return "Der Tierarzt " + this.getName() + " hat das Tier geheilt.";
        }else{
            return "Das Tier ist nicht krank, es muss nicht geheilt werden.";
        }

        
    }
    
}
