package src.com.tierpark.GUI.table;

/*
 * Editor für die Bearbeiten-Schaltfläche in der Gehege-Tabelle.
 * Öffnet einen Dialog zur Anpassung eines Geheges.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.dialogs.EditGehegeDialog;
import src.com.tierpark.GUI.panels.GehegePanel;
import src.com.tierpark.gehege.*;

import javax.swing.*;
import java.awt.*;

public class EditButtonEditor extends DefaultCellEditor {

    private final JButton button;
    private final TierparkController controller;
    private final GehegePanel panel;
    private final JFrame parent;
    private int row;

    public EditButtonEditor(JCheckBox box, TierparkController controller, GehegePanel panel, JFrame parent) {
        super(box);
        this.controller = controller;
        this.panel = panel;
        this.parent = parent;
        button = new JButton("Bearbeiten");
        button.addActionListener(e -> {
            fireEditingStopped();
            Gehege g = controller.getGehegeListe().get(row);
            new EditGehegeDialog(parent, g, panel);
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
