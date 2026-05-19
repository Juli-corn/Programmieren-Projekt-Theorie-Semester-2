package src.com.tierpark.GUI.table;

/*
 * Editor für die Hinzufügen-Schaltfläche im Tier-Zuordnungsdialog.
 * Ordnet das ausgewählte Tier einem Gehege zu.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.panels.GehegePanel;
import src.com.tierpark.gehege.*;
import src.com.tierpark.tier.Tier;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddButtonEditor extends DefaultCellEditor {

    private final JButton button;
    private final TierparkController controller;
    private final Gehege gehege;
    private final Runnable refreshCallback;
    private int row;
    private Tier selectedTier;

    public AddButtonEditor(JCheckBox checkBox, TierparkController controller, Gehege gehege, Runnable refreshCallback, GehegePanel parentPanel) {
        super(checkBox);
        this.controller = controller;
        this.gehege = gehege;
        this.refreshCallback = refreshCallback;

        button = new JButton("Hinzufügen");
        button.addActionListener(e -> {
            fireEditingStopped();
            if (selectedTier == null) {
                return;
            }

            String message = controller.assignTierToGehege(gehege, selectedTier);
            JOptionPane.showMessageDialog(button, message);
            refreshCallback.run();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        List<Tier> available = controller.getAvailableTiereForGehege(gehege);
        selectedTier = row >= 0 && row < available.size() ? available.get(row) : null;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Hinzufügen";
    }
}
