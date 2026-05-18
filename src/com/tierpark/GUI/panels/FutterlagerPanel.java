package GUI.panels;

/*
 * Panel zur Anzeige und Verwaltung des Futterlagers.
 * Zeigt Bestand, Maximal- und Mindestbestände und ermöglicht die Anpassung der Maximalmenge.
 */

import GUI.controller.TierparkController;
import GUI.table.ButtonRenderer;
import GUI.table.FutterlagerEditButtonEditor;
import futter.Futterlager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FutterlagerPanel {

    private final TierparkController controller;
    private final DefaultTableModel model;
    private final JFrame frame;

    // Konstruktor erstellt die Futterlagerverwaltung und initialisiert die Tabelle.
    public FutterlagerPanel(JFrame parent, TierparkController controller) {
        this.controller = controller;
        frame = new JFrame("Futterlagerverwaltung");
        frame.setSize(700, 300);
        frame.setLocationRelativeTo(parent);
        frame.setLocation(parent.getX() + 40, parent.getY() + 40);
        frame.setLayout(new BorderLayout());

        String[] columns = {"Art", "Bestand (vorhanden/max)", "Mindestbestand", "Bearbeiten"};
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        table.getColumn("Bearbeiten").setCellRenderer(new ButtonRenderer());
        table.getColumn("Bearbeiten").setCellEditor(new FutterlagerEditButtonEditor(new JCheckBox(), controller, this, frame));

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        refresh();
        frame.setVisible(true);
    }

    public void refresh() {
        model.setRowCount(0);
        for (Futterlager lager : controller.getFutterlagerListe()) {
            model.addRow(new Object[]{
                    lager.getFutterart(),
                    lager.getFutter() + " / " + lager.getMaxFutter(),
                    lager.getMinFutter(),
                    "Bearbeiten"
            });
        }
    }
}
