package src.com.tierpark.GUI.table;

/*
 * Editor für den Öffnen-Button der Gehege-Tabelle.
 * Öffnet das Dialogfenster mit den Tieren des ausgewählten Geheges.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.dialogs.GehegeAnimalsDialog;
import src.com.tierpark.GUI.panels.GehegePanel;
import src.com.tierpark.gehege.*;

import javax.swing.*;
import java.awt.*;

public class GehegeButtonEditor extends DefaultCellEditor {

    private final JButton button;
    private final TierparkController controller;
    private final GehegePanel panel;
    private final JFrame parent;
    private int row;

    public GehegeButtonEditor(JCheckBox box, TierparkController controller, GehegePanel panel, JFrame parent) {
        super(box);
        this.controller = controller;
        this.panel = panel;
        this.parent = parent;
        button = new JButton("Öffnen");
        button.addActionListener(e -> {
            fireEditingStopped();
            Gehege g = controller.getGehegeListe().get(row);
            new GehegeAnimalsDialog(parent, controller, g, panel);
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
        return "Öffnen";
    }
}
