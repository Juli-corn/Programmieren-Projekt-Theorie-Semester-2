package app;

import javax.swing.SwingUtilities;

import futter.*;
import personal.*;
import gehege.*;
import tier.*;


public class main{

    public static int id = 0;

    private static Pfleger[] pfleger = new Pfleger[10];
    private static Tierarzt[] tierarzt = new Tierarzt[10];
    public static Fischlager fischlager = new Fischlager(1, "Fischlager", 1000);
    public static Fleischlager fleischlager = new Fleischlager(2, "Fleischlager", 1000);
    public static Pflanzenlager pflanzenlager = new Pflanzenlager(3, "Pflanzenlager", 1000);

    public static void main(String[] args){
    
        
      SwingUtilities.invokeLater(() -> new TierparkGUI().setVisible(true));
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