package GUI.table;

/*
 * Editor für die Füttern-Schaltfläche in der Gehege-Tabelle.
 * Löst Fütterungsaktionen aus und zeigt Ergebnisdialoge an.
 */

import GUI.controller.TierparkController;
import GUI.panels.GehegePanel;
import gehege.Gehege;

import javax.swing.*;
import java.awt.*;

public class FeedButtonEditor extends DefaultCellEditor {

    private final JButton button;
    private final TierparkController controller;
    private final GehegePanel panel;
    private final JFrame parent;
    private int row;

    public FeedButtonEditor(JCheckBox box, TierparkController controller, GehegePanel panel, JFrame parent) {
        super(box);
        this.controller = controller;
        this.panel = panel;
        this.parent = parent;
        button = new JButton("Füttern");
        button.addActionListener(e -> {
            fireEditingStopped();
            Gehege g = controller.getGehegeListe().get(row);
            String result = controller.fuetternGehege(g);
            JOptionPane.showMessageDialog(parent, result);
            panel.refresh();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Füttern";
    }
}
