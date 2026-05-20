package src.com.tierpark.GUI.dialogs;

/*
 * Dialog zum Bearbeiten eines vorhandenen Geheges.
 * Nutzer können maximalen Tierbestand und die Fütterungszeit anpassen.
 */

import src.com.tierpark.GUI.panels.GehegePanel;
import src.com.tierpark.gehege.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditGehegeDialog extends JDialog {

    /**
     *
     * 7.1.1 Exceptions in Java. Java ist auch eine Insel JavaBuchFrame.PDF https://www.raffia.ch/content/docplus/Java%20ist%20auch%20eine%20Insel%20-%20Ullenboom%202001.pdf
     *
     */
    public EditGehegeDialog(JFrame parent, Gehege gehege, GehegePanel panel) {
        super(parent, "Gehege bearbeiten", true);

        setSize(300, 250);
        setLayout(new GridLayout(4, 2));

        JTextField maxTiereField = new JTextField(String.valueOf(gehege.getMaxTiere()));
        JTextField fuetterungField = new JTextField(gehege.getFuetterungszeit());

        add(new JLabel("Typ:"));
        add(new JLabel(gehege.getTyp()));

        add(new JLabel("Max Tiere:"));
        add(maxTiereField);

        add(new JLabel("Fütterungszeit:"));
        add(fuetterungField);

        JButton saveBtn = new JButton("Speichern");
        saveBtn.addActionListener(e -> {
            try {
                int max = Integer.parseInt(maxTiereField.getText());
                String ft = fuetterungField.getText();
                    try {
                        LocalTime.parse(ft, DateTimeFormatter.ofPattern("SS:MM"));
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(this, "Bitte Fütterungszeit im Format SS:MM eingeben.");
                        return;
                    }
                if (max < gehege.getAnzahlUntergebrachteTiere()) {
                    JOptionPane.showMessageDialog(this, "Max Tiere darf nicht kleiner als die aktuelle Anzahl der Tiere sein.");
                    return;
                }

                gehege.setMaxTiere(max);
                gehege.setFuetterungszeit(ft);
                panel.refresh();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Bitte gültige Zahl eingeben!");
            }
        });

        add(new JLabel());
        add(saveBtn);

        setLocationRelativeTo(parent);
        setLocation(parent.getX() + 40, parent.getY() + 40);
        setVisible(true);
    }
}
