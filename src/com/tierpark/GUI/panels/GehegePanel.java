package GUI.panels;

import GUI.controller.TierparkController;
import GUI.dialogs.CreateGehegeDialog;
import GUI.table.ButtonRenderer;
import GUI.table.EditButtonEditor;
import GUI.table.GehegeButtonEditor;
import gehege.Gehege;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GehegePanel {

    private final TierparkController controller;
    private final DefaultTableModel model;
    private final JFrame frame;

    public GehegePanel(JFrame parent, TierparkController controller) {
        this.controller = controller;
        frame = new JFrame("Gehege");
        frame.setSize(700, 350);
        frame.setLocationRelativeTo(parent);
        frame.setLayout(new BorderLayout());

        String[] columns = {"Typ", "T/T", "FT", "Tiere", "Bearbeiten"};
        model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4;
            }
        };

        table.getColumn("Tiere").setCellRenderer(new ButtonRenderer());
        table.getColumn("Tiere").setCellEditor(new GehegeButtonEditor(new JCheckBox(), controller, this, frame));

        table.getColumn("Bearbeiten").setCellRenderer(new ButtonRenderer());
        table.getColumn("Bearbeiten").setCellEditor(new EditButtonEditor(new JCheckBox(), controller, this, frame));

        JButton create = new JButton("Gehege erstellen");
        create.addActionListener(e -> new CreateGehegeDialog(frame, controller, this));

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(create, BorderLayout.SOUTH);

        refresh();
        frame.setVisible(true);
    }

    public void refresh() {
        model.setRowCount(0);
        for (Gehege g : controller.getGehegeListe()) {
            model.addRow(new Object[]{
                g.getTyp(),
                g.getAnzahlUntergebrachteTiere() + "/" + g.getMaxTiere(),
                g.getFuetterungszeit(),
                "Öffnen",
                "Bearbeiten"
            });
        }
    }
}
