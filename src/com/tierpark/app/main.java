package app;

import javax.swing.SwingUtilities;

import GUI.main.TierparkGUI;
import GUI.controller.TierparkController;
import futter.*;
import personal.*;
import gehege.*;
import tier.*;
import tier.tiere.*;


public class main{

    public static int id = 0;

    private static Pfleger[] pfleger = new Pfleger[10];
    private static Tierarzt[] tierarzt = new Tierarzt[10];
    public static Fischlager fischlager = new Fischlager(1, "Fischlager", 1000);
    public static Fleischlager fleischlager = new Fleischlager(2, "Fleischlager", 1000);
    public static Pflanzenlager pflanzenlager = new Pflanzenlager(3, "Pflanzenlager", 1000);

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
        // Create enclosures
        Gehege savanne = new Savannengehege(5, "08:00");
        Gehege dschungel = new Dschungelgehege(8, "10:00");
        
        controller.addGehege(savanne);
        controller.addGehege(dschungel);
        
        // Create animals for Savanne
        Tier lion = new Warzenschwein("Simba", 8, 15);
        Tier zebra = new Erdmännchen("Zuri", 5, 4);
        Tier giraffe = new Quokka("Gigi", 6, 3);
        
        controller.addTier(lion);
        controller.addTier(zebra);
        controller.addTier(giraffe);
        
        // Assign animals to Savanne
        controller.assignTierToGehege(savanne, lion);
        controller.assignTierToGehege(savanne, zebra);
        controller.assignTierToGehege(savanne, giraffe);
        
        // Create animals for Dschungel
        Tier monkey = new Faultier("Coco", 7, 5);
        Tier snake = new Krokodil("Sly", 10, 12);
        Tier bird = new Pinguin("Pingu", 3, 2);
        
        controller.addTier(monkey);
        controller.addTier(snake);
        controller.addTier(bird);
        
        // Assign animals to Dschungel
        controller.assignTierToGehege(dschungel, monkey);
        controller.assignTierToGehege(dschungel, snake);
        controller.assignTierToGehege(dschungel, bird);
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