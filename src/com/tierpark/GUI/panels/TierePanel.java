package GUI.panels;

import GUI.controller.TierparkController;
import GUI.dialogs.CreateTierDialog;
import tier.Tier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TierePanel {

    private final TierparkController controller;
    private final DefaultTableModel model;
    private final JFrame frame;

    public TierePanel(JFrame parent, TierparkController controller) {
        this.controller = controller;
        frame = new JFrame("Tiere");
        frame.setSize(700, 250);
        frame.setLocationRelativeTo(parent);
        frame.setLayout(new BorderLayout());

        String[] cols = {"Name", "Alter", "Art", "Lieblingsfutter", "Futtermenge", "Gehegetyp"};
        model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);

        JButton btn = new JButton("Tier erstellen");
        btn.addActionListener(e -> new CreateTierDialog(frame, controller, this));

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(btn, BorderLayout.SOUTH);

        refresh();
        frame.setVisible(true);
    }

    public void refresh() {
        model.setRowCount(0);
        for (Tier t : controller.getTierListe()) {
            model.addRow(new Object[]{
                t.getName(),
                t.getAlter(),
                t.getArt(),
                t.getLieblingsfutter(),
                t.getFuttermenge(),
                t.getGehegeTyp()
            });
        }
    }
}
