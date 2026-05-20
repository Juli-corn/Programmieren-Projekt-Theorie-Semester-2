package src.com.tierpark.GUI.table;

/*
 * Editor für die Bearbeiten-Schaltfläche im Futterlager-Table.
 * Erlaubt das Anpassen der Maximalbestände eines Lagers.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.panels.FutterlagerPanel;
import src.com.tierpark.futter.Futterlager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FutterlagerEditButtonEditor extends DefaultCellEditor {

    private final JButton button;
    private final TierparkController controller;
    private final FutterlagerPanel panel;
    private final JFrame parent;
    private int row;

    /**
     *
     * 7.1.1 Exceptions in Java. Java ist auch eine Insel JavaBuchFrame.PDF https://www.raffia.ch/content/docplus/Java%20ist%20auch%20eine%20Insel%20-%20Ullenboom%202001.pdf
     *
     */
    public FutterlagerEditButtonEditor(JCheckBox box, TierparkController controller, FutterlagerPanel panel, JFrame parent) {
        super(box);
        this.controller = controller;
        this.panel = panel;
        this.parent = parent;
        button = new JButton("Bearbeiten");
        button.addActionListener(e -> {
            fireEditingStopped();
            List<Futterlager> lagerListe = controller.getFutterlagerListe();
            Futterlager lager = lagerListe.get(row);
            String input = JOptionPane.showInputDialog(parent,
                    "Neue Maximalmenge für " + lager.getFutterart() + " (kg):",
                    lager.getMaxFutter());
            if (input == null) {
                return;
            }
            try {
                int max = Integer.parseInt(input.trim());
                if (max < lager.getMinFutter()) {
                    JOptionPane.showMessageDialog(parent, "Maximalmenge darf nicht kleiner als der Mindestbestand sein: " + lager.getMinFutter());
                    return;
                }
                lager.setMaxFutter(max);
                panel.refresh();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Bitte geben Sie eine gültige Zahl ein.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(parent, ex.getMessage());
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Bearbeiten";
    }
}
