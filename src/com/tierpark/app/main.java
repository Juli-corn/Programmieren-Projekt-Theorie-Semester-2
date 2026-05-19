package src.com.tierpark.app;

import javax.swing.SwingUtilities;

import src.com.tierpark.GUI.main.TierparkGUI;
import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.personal.*;
import src.com.tierpark.gehege.*;
import src.com.tierpark.tier.*;
import src.com.tierpark.tier.tiere.*;

public class main{

    public static int id = 0;

    public static void main(String[] args){
        
        // Create test data
        TierparkController controller = new TierparkController();
        initializeTestData(controller);
        
        SwingUtilities.invokeLater(() -> {
            TierparkGUI gui = new TierparkGUI();
            gui.setController(controller);
            gui.setVisible(true);
        });
    }
    
    private static void initializeTestData(TierparkController controller) {
        // Gehege erstellen
        Gehege savanne = new Savannengehege(5, "08:00");
        Gehege dschungel = new Dschungelgehege(8, "10:00");
        Gehege wasser = new Wassergehege(4, "09:00");
        Gehege eis = new Eisgehege(3, "11:00");
        
        controller.addGehege(savanne);
        controller.addGehege(dschungel);
        controller.addGehege(wasser);
        controller.addGehege(eis);
        
        // Tiere für Savanne
        Tier schwein = new Warzenschwein("Simba", 8, 15);
        Tier erdmann = new Erdmännchen("Zuri", 5, 1);
        Tier quokka = new Quokka("Gigi", 6, 3);
        
        controller.addTier(schwein);
        controller.addTier(erdmann);
        controller.addTier(quokka);
        
        // Tiere zu Gehege hinzufügen
        controller.assignTierToGehege(savanne, schwein);
        controller.assignTierToGehege(savanne, erdmann);
        controller.assignTierToGehege(savanne, quokka);

        // Tiere für Dschungel
        Tier faultier = new Faultier("Coco", 7, 5);
        Tier krokodil = new Krokodil("Sly", 10, 12);
        Tier capy = new Capybara("Capy", 4, 4);
        
        controller.addTier(faultier);
        controller.addTier(krokodil);
        controller.addTier(capy);
        
        // Tiere zu Gehege hinzufügen
        controller.assignTierToGehege(dschungel, faultier);
        controller.assignTierToGehege(dschungel, krokodil);
        controller.assignTierToGehege(dschungel, capy);

        // Tiere für Eisgehege
        Tier seeloewe = new Seelöwe("Luna", 4, 6);
        Tier pinguin = new Pinguin("Pingu", 3, 2);
        
        controller.addTier(seeloewe);
        controller.addTier(pinguin);    

        // Tiere zu Gehege hinzufügen
        controller.assignTierToGehege(eis, seeloewe);
        controller.assignTierToGehege(eis, pinguin);

        // Tiere für Wassergehege
        Tier axolotl = new Axolotl("Axel", 2, 1);
        Tier bastardschildkroete = new Schildkröte("Shelly", 5, 2);

        controller.addTier(axolotl);
        controller.addTier(bastardschildkroete);

        // Tiere zu Gehege hinzufügen
        controller.assignTierToGehege(wasser, axolotl);
        controller.assignTierToGehege(wasser, bastardschildkroete);

        // Angestellte erstellen
        controller.addPersonal(new Pfleger("Max Mustermann", "08:00-16:00", 3));
        controller.addPersonal(new Pfleger("Lena Becker", "09:00-17:00", 2));
        controller.addPersonal(new Tierarzt("Anna Schmidt", "10:00-18:00", 2));
        controller.addPersonal(new Tierarzt("Tom Meyer", "12:00-20:00", 3));
    }
}
/*
    Funktionen:
        - Tier Objekt erstellen + Gehege zuweisen
        - Gehege Objekt erzeugen + diese bearbeiten
        - Futterplan ausdrucken 
        - Anzeige Futterlager
        - Tier als krank markieren
        - Tierartzt einsetzen 
        - Tagesbericht drucken -> Tiere gefüttert, Wärter anwesend, (Lagerbestand), Bestellungen, bahandelte Tiere
*/