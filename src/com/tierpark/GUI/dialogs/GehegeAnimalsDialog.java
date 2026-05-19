package src.com.tierpark.GUI.dialogs;

/*
 * Dialog zur Anzeige der Tiere eines einzelnen Geheges.
 * Ermöglicht Hinzufügen und Entfernen von Tieren für das ausgewählte Gehege.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.panels.GehegePanel;
import src.com.tierpark.gehege.Gehege;
import src.com.tierpark.tier.Tier;

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
        JButton removeBtn = new JButton("Tier entfernen");
        removeBtn.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel < 0) {
                JOptionPane.showMessageDialog(this, "Bitte zuerst ein Tier auswählen.");
                return;
            }
            List<Tier> animals = getAnimalsInGehege();
            Tier toRemove = animals.get(sel);
            int confirm = JOptionPane.showConfirmDialog(this, "Soll " + toRemove.getName() + " aus dem Gehege entfernt werden?", "Bestätigen", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String result = controller.removeTierFromGehege(toRemove);
                JOptionPane.showMessageDialog(this, result);
                refresh();
                parentPanel.refresh();
            }
        });

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        refresh();
        setLocationRelativeTo(parent);
        setLocation(parent.getX() + 40, parent.getY() + 40);
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
            if (t.getInGehege() && t.getGehegeId() == gehege.getId()) {
                result.add(t);
            }
        }
        return result;
    }
}
