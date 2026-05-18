package GUI.dialogs;

/*
 * Dialog zur Erstellung eines neuen Tieres.
 * Ermöglicht die Eingabe eines Namens, Alters, der Tierart und des täglichen Futterbedarfs.
 */

import GUI.controller.TierparkController;
import GUI.panels.TierePanel;
import tier.Tier;
import tier.tiere.Ameisenbär;
import tier.tiere.Axolotl;
import tier.tiere.Braunbaer;
import tier.tiere.Capybara;
import tier.tiere.Erdmännchen;
import tier.tiere.Faultier;
import tier.tiere.Fennek;
import tier.tiere.Krokodil;
import tier.tiere.Nacktmull;
import tier.tiere.Pinguin;
import tier.tiere.Quokka;
import tier.tiere.Schildkröte;
import tier.tiere.Seelöwe;
import tier.tiere.Stinktier;
import tier.tiere.Warzenschwein;

import javax.swing.*;
import java.awt.*;

public class CreateTierDialog extends JDialog {

    public CreateTierDialog(JFrame parent, TierparkController controller, TierePanel panel) {
        super(parent, "Tier erstellen", true);

        setSize(500, 250);
        setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{
            "Ameisenbär", "Axolotl", "Braunbär", "Capybara", "Erdmännchen", "Faultier",
            "Fennek", "Krokodil", "Nacktmull", "Pinguin", "Quokka", "Olivgrüne-Bastardschildkröte",
            "Seelöwe", "Stinktier", "Warzenschwein"
        });
        JTextField foodField = new JTextField();

        add(new JLabel("Name:"));
        add(nameField);

        add(new JLabel("Alter:"));
        add(ageField);

        add(new JLabel("Art:"));
        add(typeBox);

        add(new JLabel("Futter (kg):"));
        add(foodField);

        JButton btn = new JButton("Erstellen");
        btn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                int food = Integer.parseInt(foodField.getText().trim());
                String typ = (String) typeBox.getSelectedItem();

                Tier t;
                switch (typ) {
                    case "Ameisenbär":
                        t = new Ameisenbär(name, age, food);
                        break;
                    case "Axolotl":
                        t = new Axolotl(name, age, food);
                        break;
                    case "Braunbär":
                        t = new Braunbaer(name, age, food);
                        break;
                    case "Capybara":
                        t = new Capybara(name, age, food);
                        break;
                    case "Erdmännchen":
                        t = new Erdmännchen(name, age, food);
                        break;
                    case "Faultier":
                        t = new Faultier(name, age, food);
                        break;
                    case "Fennek":
                        t = new Fennek(name, age, food);
                        break;
                    case "Krokodil":
                        t = new Krokodil(name, age, food);
                        break;
                    case "Nacktmull":
                        t = new Nacktmull(name, age, food);
                        break;
                    case "Pinguin":
                        t = new Pinguin(name, age, food);
                        break;
                    case "Quokka":
                        t = new Quokka(name, age, food);
                        break;
                    case "Olivgrüne-Bastardschildkröte":
                        t = new Schildkröte(name, age, food);
                        break;
                    case "Seelöwe":
                        t = new Seelöwe(name, age, food);
                        break;
                    case "Stinktier":
                        t = new Stinktier(name, age, food);
                        break;
                    case "Warzenschwein":
                        t = new Warzenschwein(name, age, food);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Unbekannte Tierart.");
                        return;
                }

                controller.addTier(t);
                panel.refresh();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Bitte gültige Zahlen eingeben.");
            }
        });

        add(new JLabel());
        add(btn);

        setLocationRelativeTo(parent);
        setLocation(parent.getX() + 40, parent.getY() + 40);
        setVisible(true);
    }
}
