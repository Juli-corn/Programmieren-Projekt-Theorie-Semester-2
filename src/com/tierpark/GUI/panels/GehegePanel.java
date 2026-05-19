package src.com.tierpark.GUI.panels;

/*
 * Panel zur Verwaltung und Darstellung der Gehege.
 * Enthält eine Tabelle mit Gehegedaten, Fütterungs-Buttons und Bearbeiten-Buttons.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.dialogs.CreateGehegeDialog;
import src.com.tierpark.GUI.table.ButtonRenderer;
import src.com.tierpark.GUI.table.EditButtonEditor;
import src.com.tierpark.GUI.table.GehegeButtonEditor;
import src.com.tierpark.GUI.table.FeedButtonEditor;
import src.com.tierpark.gehege.*;

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
        frame.setLocation(parent.getX() + 40, parent.getY() + 40);
        frame.setLayout(new BorderLayout());

        String[] columns = {"Typ", "akt/max", "Fütterungszeit", "Füttern", "Fisch (kg)", "Fleisch (kg)", "Pflanzen (kg)", "Tiere", "Bearbeiten"};
        model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 7 || column == 8;
            }
        };

        table.getColumn("Tiere").setCellRenderer(new ButtonRenderer());
        table.getColumn("Tiere").setCellEditor(new GehegeButtonEditor(new JCheckBox(), controller, this, frame));

        table.getColumn("Bearbeiten").setCellRenderer(new ButtonRenderer());
        table.getColumn("Bearbeiten").setCellEditor(new EditButtonEditor(new JCheckBox(), controller, this, frame));

        table.getColumn("Füttern").setCellRenderer(new ButtonRenderer());
        table.getColumn("Füttern").setCellEditor(new FeedButtonEditor(new JCheckBox(), controller, this, frame));

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
            int[] verbrauch = g.getFutterverbrauch();
            model.addRow(new Object[]{
                g.getTyp(),
                g.getAnzahlUntergebrachteTiere() + "/" + g.getMaxTiere(),
                g.getFuetterungszeit(),
                "Füttern",
                verbrauch[0],
                verbrauch[1],
                verbrauch[2],
                "Öffnen",
                "Bearbeiten"
            });
        }
    }

}
