package src.com.tierpark.GUI.panels;

/*
 * Panel zur Personalverwaltung.
 * Hier können Pfleger und Tierärzte hinzugefügt und gelöscht werden.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.personal.Pfleger;
import src.com.tierpark.personal.Personal;
import src.com.tierpark.personal.Tierarzt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PflegerPanel {

    private final TierparkController controller;
    private final DefaultTableModel model;
    private final JFrame frame;

    // Konstruktor erstellt das Panel für die Personalverwaltung.
    public PflegerPanel(JFrame parent, TierparkController controller) {
        this.controller = controller;
        frame = new JFrame("Personalverwaltung");
        frame.setSize(700, 350);
        frame.setLocationRelativeTo(parent);
        frame.setLocation(parent.getX() + 40, parent.getY() + 40);
        frame.setLayout(new BorderLayout());

        String[] columns = {"Name", "Art", "Schicht", "Kapazität", "Erledigt", "Status"};
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Mitarbeiter hinzufügen");
        JButton deleteButton = new JButton("Mitarbeiter löschen");

        addButton.addActionListener(e -> createPersonal());
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Bitte wählen Sie zuerst einen Mitarbeiter aus.");
                return;
            }
            Personal p = controller.getPersonalListe().get(row);
            controller.removePersonal(p);
            refresh();
        });

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        refresh();
        frame.setVisible(true);
    }

    /**
     * 7.1.1 Exceptions in Java. Java ist auch eine Insel JavaBuchFrame.PDF https://www.raffia.ch/content/docplus/Java%20ist%20auch%20eine%20Insel%20-%20Ullenboom%202001.pdf
     */
    private void createPersonal() {
        String name = JOptionPane.showInputDialog(frame, "Name des Mitarbeiters:");
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        String art = (String) JOptionPane.showInputDialog(frame, "Art des Personals:", "Personaltyp",
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Pfleger", "Tierarzt"}, "Pfleger");
        if (art == null) {
            return;
        }
        String schicht = JOptionPane.showInputDialog(frame, "Schichtzeit (Format SS:MM-HH:MM):", "08:00-16:00");
        if (schicht == null || schicht.trim().isEmpty()) {
            return;
        }
        String kapazitaetText = JOptionPane.showInputDialog(frame, "Maximale Anzahl zu betreuender Tiere pro Schicht:", "5");
        if (kapazitaetText == null || kapazitaetText.trim().isEmpty()) {
            return;
        }

        try {
            int kapazitaet = Integer.parseInt(kapazitaetText.trim());
            if (kapazitaet < 1) {
                JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige positive Zahl ein.");
                return;
            }

            if (art.equals("Pfleger")) {
                controller.addPersonal(new Pfleger(name.trim(), schicht.trim(), kapazitaet));
            } else {
                controller.addPersonal(new Tierarzt(name.trim(), schicht.trim(), kapazitaet));
            }
            refresh();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige Zahl ein.");
        }
    }

    public void refresh() {
        model.setRowCount(0);
        for (Personal p : controller.getPersonalListe()) {
            model.addRow(new Object[]{
                    p.getName(),
                    p.getJob(),
                    p.getSchicht(),
                    p.getMaxAufgabenProSchicht(),
                    p.getAufgabenErledigt(),
                    p.getVerfuegbarkeit()
            });
        }
    }
}
