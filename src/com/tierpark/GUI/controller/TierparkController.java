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

    /**
     * Fügt ein Gehege hinzu.
     *
     * @param g hinzuzufügendes Gehege
     */
    public void addGehege(Gehege g) {
        gehegeListe.add(g);
    }

    /**
     * Fügt ein Tier hinzu.
     *
     * @param t hinzuzufügendes Tier
     */
    public void addTier(Tier t) {
        tierListe.add(t);
    }

    /**
     * Ermittelt verfügbare Tiere für ein Gehege.
     *
     * @param gehege zum Zielgehege
     * @return Liste verfügbarer Tiere
     */
    public List<Tier> getAvailableTiereForGehege(Gehege gehege) {
        List<Tier> result = new ArrayList<>();
        for (Tier t : tierListe) {
            if (!t.getInGehege() && t.getGehegeTyp().equals(gehege.getTyp())) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Ordnet ein Tier einem Gehege zu.
     *
     * @param gehege Zielgehege
     * @param tier zuzuordnendes Tier
     * @return Ergebnis der Zuordnung
     */
    public String assignTierToGehege(Gehege gehege, Tier tier) {
        String message = gehege.tierHinzufuegen(tier);
        if (message.contains("wurde erfolgreich hinzugefügt")) {
            tier.setInGehege(true);
        }
        return message;
    }
}
