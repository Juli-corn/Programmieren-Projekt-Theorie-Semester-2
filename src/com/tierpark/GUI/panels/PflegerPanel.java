package GUI.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PflegerPanel {

    public PflegerPanel(JFrame parent) {
        JFrame frame = new JFrame("Pfleger");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(parent);

        String[] columns = {"Name", "Art", "Schicht"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        model.addRow(new Object[]{"Max Mustermann", "Tierpfleger", "08:00 - 16:00"});
        model.addRow(new Object[]{"Anna Schmidt", "Tierarzt", "10:00 - 18:00"});

        frame.add(new JScrollPane(new JTable(model)));
        frame.setVisible(true);
    }
}
