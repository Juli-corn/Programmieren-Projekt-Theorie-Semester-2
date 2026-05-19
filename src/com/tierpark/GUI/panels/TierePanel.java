package src.com.tierpark.GUI.panels;

/*
 * Panel zur Anzeige aller Tiere im Tierpark.
 * Über dieses Fenster können Tiere erstellt, gelöscht und als krank markiert werden.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.dialogs.CreateTierDialog;
import src.com.tierpark.tier.Tier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TierePanel {

    private final TierparkController controller;
    private final DefaultTableModel model;
    private final JFrame frame;

    // Konstruktor erstellt das Tierverwaltungsfenster mit den Hauptaktionen.
    public TierePanel(JFrame parent, TierparkController controller) {
        this.controller = controller;
        frame = new JFrame("Tiere");
        frame.setSize(800, 320);
        frame.setLocationRelativeTo(parent);
        frame.setLocation(parent.getX() + 40, parent.getY() + 40);
        frame.setLayout(new BorderLayout());

        String[] cols = {"Name", "Alter", "Art", "Lieblingsfutter", "Futtermenge", "Gehegetyp", "Krank"};
        model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Tier erstellen");
        JButton deleteButton = new JButton("Tier löschen");
        JButton sickButton = new JButton("Als krank markieren");

        createButton.addActionListener(e -> new CreateTierDialog(frame, controller, this));
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Bitte wählen Sie zuerst ein Tier aus.");
                return;
            }
            Tier t = controller.getTierListe().get(row);
            String message = controller.removeTier(t);
            JOptionPane.showMessageDialog(frame, message);
            refresh();
        });
        sickButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Bitte wählen Sie zuerst ein Tier aus.");
                return;
            }
            Tier t = controller.getTierListe().get(row);
            String message = controller.markTierAsKrank(t);
            JOptionPane.showMessageDialog(frame, message);
            refresh();
        });

        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sickButton);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        refresh();
        frame.setVisible(true);
    }

    public void refresh() {
        model.setRowCount(0);
        for (Tier t : controller.getTierListe()) {
            model.addRow(new Object[]{
                    t.getName(),
                    t.getAlter(),
                    t.getArt(),
                    t.getLieblingsfutter(),
                    t.getFuttermenge(),
                    t.getGehegeTyp(),
                    t.getIstKrank() ? "Ja" : "Nein"
            });
        }
    }
}
