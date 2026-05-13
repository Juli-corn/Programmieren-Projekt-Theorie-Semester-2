package GUI.controller;

import gehege.Gehege;
import tier.Tier;

import java.util.ArrayList;
import java.util.List;

public class TierparkController {

    private final List<Gehege> gehegeListe = new ArrayList<>();
    private final List<Tier> tierListe = new ArrayList<>();

    public List<Gehege> getGehegeListe() {
        return gehegeListe;
    }

    public List<Tier> getTierListe() {
        return tierListe;
    }

    public void addGehege(Gehege g) {
        gehegeListe.add(g);
    }

    public void addTier(Tier t) {
        tierListe.add(t);
    }

    public List<Tier> getAvailableTiereForGehege(Gehege gehege) {
        List<Tier> result = new ArrayList<>();
        for (Tier t : tierListe) {
            if (!t.getInGehege() && t.getGehegeTyp().equals(gehege.getTyp())) {
                result.add(t);
            }
        }
        return result;
    }

    public String assignTierToGehege(Gehege gehege, Tier tier) {
        String message = gehege.tierHinzufuegen(tier);
        if (message.contains("wurde erfolgreich hinzugefügt")) {
            tier.setInGehege(true);
        }
        return message;
    }
}
