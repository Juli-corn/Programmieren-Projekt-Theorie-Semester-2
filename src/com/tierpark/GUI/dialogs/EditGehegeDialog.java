package GUI.dialogs;

import GUI.panels.GehegePanel;
import gehege.Gehege;

import javax.swing.*;
import java.awt.*;

public class EditGehegeDialog extends JDialog {

    public EditGehegeDialog(JFrame parent, Gehege gehege, GehegePanel panel) {
        super(parent, "Gehege bearbeiten", true);

        setSize(300, 250);
        setLayout(new GridLayout(4, 2));

        JTextField maxTiereField = new JTextField(String.valueOf(gehege.getMaxTiere()));
        JTextField fuetterungField = new JTextField(gehege.getFuetterungszeit());

        add(new JLabel("Typ:"));
        add(new JLabel(gehege.getTyp()));

        add(new JLabel("Max Tiere:"));
        add(maxTiereField);

        add(new JLabel("Fütterungszeit:"));
        add(fuetterungField);

        JButton saveBtn = new JButton("Speichern");
        saveBtn.addActionListener(e -> {
            try {
                int max = Integer.parseInt(maxTiereField.getText());
                String ft = fuetterungField.getText();

                gehege.setMaxTiere(max);
                gehege.setFuetterungszeit(ft);
                panel.refresh();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Bitte gültige Zahl eingeben!");
            }
        });

        add(new JLabel());
        add(saveBtn);

        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
