package src.com.tierpark.GUI.controller;

/*
 * Zentrale Steuerung für den Tierpark.
 * Verwaltet Gehege, Tiere, Personal und Futterlager.
 * Liefert GUI-kompatible Daten sowie Aktionen für Fütterung und Krankheitsverwaltung.
 */

import src.com.tierpark.app.main;
import src.com.tierpark.futter.Fleischlager;
import src.com.tierpark.futter.Fischlager;
import src.com.tierpark.futter.Futterlager;
import src.com.tierpark.futter.Pflanzenlager;
import src.com.tierpark.gehege.Gehege;
import src.com.tierpark.personal.Pfleger;
import src.com.tierpark.personal.Personal;
import src.com.tierpark.personal.Tierarzt;
import src.com.tierpark.tier.Tier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TierparkController {

    private final List<Gehege> gehegeListe = new ArrayList<>();
    private final List<Tier> tierListe = new ArrayList<>();
    private final List<Personal> personalListe = new ArrayList<>();
    private final Fischlager fischlager;
    private final Fleischlager fleischlager;
    private final Pflanzenlager pflanzenlager;
    private final List<Tier> pendingKrank = new ArrayList<>();

    public TierparkController() {
        this.fischlager = new Fischlager(main.id++, "Fischlager", 50);
        this.fleischlager = new Fleischlager(main.id++, "Fleischlager", 50);
        this.pflanzenlager = new Pflanzenlager(main.id++, "Pflanzenlager", 50);
        updateFutterlagerMinima();
    }

    public List<Gehege> getGehegeListe() {
        return gehegeListe;
    }

    public List<Tier> getTierListe() {
        return tierListe;
    }

    public List<Personal> getPersonalListe() {
        return personalListe;
    }

    public List<Pfleger> getPflegerListe() {
        List<Pfleger> result = new ArrayList<>();
        for (Personal p : personalListe) {
            if (p instanceof Pfleger) {
                result.add((Pfleger) p);
            }
        }
        return result;
    }

    public List<Tierarzt> getTierarztListe() {
        List<Tierarzt> result = new ArrayList<>();
        for (Personal p : personalListe) {
            if (p instanceof Tierarzt) {
                result.add((Tierarzt) p);
            }
        }
        return result;
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

    public void addGehege(Gehege g) {
        gehegeListe.add(g);
    }

    public void addTier(Tier t) {
        tierListe.add(t);
        updateFutterlagerMinima();
    }

    public String assignTierToGehege(Gehege gehege, Tier tier) {
        String message = gehege.tierHinzufuegen(tier);
        if (message.contains("wurde erfolgreich hinzugefügt")) {
            tier.setInGehege(true);
            tier.setGehegeId(gehege.getId());
        }
        return message;
    }

    public List<Futterlager> getFutterlagerListe() {
        return Arrays.asList(fischlager, fleischlager, pflanzenlager);
    }

    public Fischlager getFischlager() {
        return fischlager;
    }

    public Fleischlager getFleischlager() {
        return fleischlager;
    }

    public Pflanzenlager getPflanzenlager() {
        return pflanzenlager;
    }

    public void updateFutterlagerMinima() {
        int fish = 0;
        int meat = 0;
        int plants = 0;
        for (Tier t : tierListe) {
            switch (t.getLieblingsfutter()) {
                case "Fisch", "Fischfutter" -> fish += t.getFuttermenge();
                case "Fleisch", "Fleischfutter" -> meat += t.getFuttermenge();
                case "Pflanzen", "Pflanzenfutter" -> plants += t.getFuttermenge();
            }
        }
        fischlager.setMinFutter(fish);
        fleischlager.setMinFutter(meat);
        pflanzenlager.setMinFutter(plants);
    }

    public void addPersonal(Personal p) {
        personalListe.add(p);
        processPendingKrank();
    }

    public void removePersonal(Personal p) {
        personalListe.remove(p);
    }

    public int getAvailablePersonalCount() {
        int count = 0;
        for (Personal p : personalListe) {
            if (p.isVerfuegbar()) {
                count++;
            }
        }
        return count;
    }

    public Tierarzt getAvailableTierarzt() {
        for (Personal p : personalListe) {
            if (p instanceof Tierarzt && p.isVerfuegbar()) {
                return (Tierarzt) p;
            }
        }
        return null;
    }

    public String markTierAsKrank(Tier tier) {
        if (!tier.getIstKrank()) {
            tier.setIstKrank(true);
        }

        Tierarzt tierarzt = getAvailableTierarzt();
        if (tierarzt == null) {
            if (!pendingKrank.contains(tier)) pendingKrank.add(tier);
            return "Tier " + tier.getName() + " ist krank. Kein Tierarzt ist derzeit verfügbar. Wird in Warteliste aufgenommen.";
        }

        return tierarzt.heileTier(tier);
    }

    public String processPendingKrank() {
        if (pendingKrank.isEmpty()) return "";
        StringBuilder messages = new StringBuilder();
        List<Tier> healed = new ArrayList<>();
        for (Tier t : new ArrayList<>(pendingKrank)) {
            Tierarzt ta = getAvailableTierarzt();
            if (ta == null) break;
            String res = ta.heileTier(t);
            healed.add(t);
            if (messages.length() > 0) {
                messages.append("\n");
            }
            messages.append(res);
        }
        pendingKrank.removeAll(healed);
        return messages.toString();
    }

    public String removeTierFromGehege(Tier tier) {
        for (Gehege g : gehegeListe) {
            String result = g.tierEntfernen(tier);
            if (result.contains("wurde erfolgreich entfernt")) {
                tier.setInGehege(false);
                tier.setGehegeId(-1);
                updateFutterlagerMinima();
                return result;
            }
        }
        return "Das Tier " + tier.getName() + " ist nicht in einem Gehege.";
    }

    public String removeTier(Tier tier) {
        for (Gehege g : gehegeListe) {
            String result = g.tierEntfernen(tier);
            if (result.contains("wurde erfolgreich entfernt")) {
                tier.setInGehege(false);
                break;
            }
        }

        if (tierListe.remove(tier)) {
            tier.setInGehege(false);
            tier.setGehegeId(-1);
            updateFutterlagerMinima();
            return "Tier " + tier.getName() + " wurde gelöscht.";
        }
        return "Tier " + tier.getName() + " wurde nicht gefunden.";
    }

    public String fuetternGehege(Gehege gehege) {
        if (gehege.getHeuteGefüttert()) {
            return "Das Gehege wurde heute bereits gefüttert.";
        }

        for (Personal p : personalListe) {
            if (p instanceof Pfleger) {
                Pfleger pf = (Pfleger) p;
                if (pf.isVerfuegbar()) {
                    String result = pf.füttern(gehege);
                    if (!result.contains("gefüttert")) {
                        return result;
                    }
                    StringBuilder builder = new StringBuilder(result);
                    int[] verbrauch = gehege.getFutterverbrauch();
                    if (verbrauch[0] > 0) {
                        builder.append(" \n").append(fischlager.ausgabe(verbrauch[0]));
                    }
                    if (verbrauch[1] > 0) {
                        builder.append(" \n").append(fleischlager.ausgabe(verbrauch[1]));
                    }
                    if (verbrauch[2] > 0) {
                        builder.append(" \n").append(pflanzenlager.ausgabe(verbrauch[2]));
                    }
                    return builder.toString();
                }
            }
        }
        return "Kein verfügbarer Pfleger zum Füttern gefunden.";
    }
}
