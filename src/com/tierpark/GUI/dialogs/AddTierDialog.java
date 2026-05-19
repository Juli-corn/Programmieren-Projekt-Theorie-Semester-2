package src.com.tierpark.GUI.dialogs;

/*
 * Dialog zum Hinzufügen von Tieren in ein bestehendes Gehege.
 * Zeigt nur freie Tiere mit passendem Gehegetyp an.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.panels.GehegePanel;
import src.com.tierpark.GUI.table.AddButtonEditor;
import src.com.tierpark.GUI.table.ButtonRenderer;
import src.com.tierpark.gehege.Gehege;
import src.com.tierpark.tier.Tier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AddTierDialog extends JDialog {

    private final TierparkController controller;
    private final Gehege gehege;
    private final DefaultTableModel model;
    private final GehegePanel parentPanel;

    public AddTierDialog(JFrame parent, TierparkController controller, Gehege gehege, GehegePanel parentPanel) {
        super(parent, "Tiere zum Hinzufügen", true);
        this.controller = controller;
        this.gehege = gehege;
        this.parentPanel = parentPanel;

        setSize(600, 300);
        setLayout(new BorderLayout());

        String[] columns = {"Art", "Futterverbrauch", "Name", "Alter", "Hinzufügen"};
        model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        table.getColumn("Hinzufügen").setCellRenderer(new ButtonRenderer());
        table.getColumn("Hinzufügen").setCellEditor(new AddButtonEditor(new JCheckBox(), controller, gehege, this::refresh, parentPanel));

        add(new JScrollPane(table), BorderLayout.CENTER);
        refresh();

        setLocationRelativeTo(parent);
        setLocation(parent.getX() + 40, parent.getY() + 40);
        setVisible(true);
    }

    public void refresh() {
        model.setRowCount(0);
        List<Tier> available = controller.getAvailableTiereForGehege(gehege);
        for (Tier t : available) {
            model.addRow(new Object[]{
                t.getArt(),
                t.getFuttermenge(),
                t.getName(),
                t.getAlter(),
                "Hinzufügen"
            });
        }
    }
}
