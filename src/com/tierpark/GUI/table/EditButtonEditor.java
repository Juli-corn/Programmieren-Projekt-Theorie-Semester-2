package GUI.table;

import GUI.controller.TierparkController;
import GUI.dialogs.EditGehegeDialog;
import GUI.panels.GehegePanel;
import gehege.Gehege;

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
