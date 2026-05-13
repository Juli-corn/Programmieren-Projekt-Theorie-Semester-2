package GUI.dialogs;

import GUI.controller.TierparkController;
import GUI.panels.GehegePanel;
import gehege.Gehege;
import tier.Tier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GehegeAnimalsDialog extends JDialog {

    private final TierparkController controller;
    private final Gehege gehege;
    private final DefaultTableModel model;
    private final GehegePanel parentPanel;
    private final JFrame parentFrame;

    public GehegeAnimalsDialog(JFrame parent, TierparkController controller, Gehege gehege, GehegePanel parentPanel) {
        super(parent, "Tiere in " + gehege.getTyp(), true);
        this.controller = controller;
        this.gehege = gehege;
        this.parentPanel = parentPanel;
        this.parentFrame = parent;

        setSize(600, 350);
        setLayout(new BorderLayout());

        String[] columns = {"Name", "Alter", "Art", "Futterverbrauch (kg)"};
        model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Tier hinzufügen");
        addBtn.addActionListener(e -> {
            new AddTierDialog(parentFrame, controller, gehege, parentPanel);
            refresh();
        });

        buttonPanel.add(addBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        refresh();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public void refresh() {
        model.setRowCount(0);
        List<Tier> animals = getAnimalsInGehege();
        for (Tier t : animals) {
            model.addRow(new Object[]{
                t.getName(),
                t.getAlter(),
                t.getArt(),
                t.getFuttermenge()
            });
        }
    }

    private List<Tier> getAnimalsInGehege() {
        List<Tier> result = new ArrayList<>();
        for (Tier t : controller.getTierListe()) {
            if (t.getInGehege() && t.getGehegeTyp().equals(gehege.getTyp())) {
                result.add(t);
            }
        }
        return result;
    }
}
