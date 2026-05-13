package GUI.dialogs;

import GUI.controller.TierparkController;
import GUI.panels.GehegePanel;
import gehege.Dschungelgehege;
import gehege.Gehege;
import gehege.Savannengehege;

import javax.swing.*;
import java.awt.*;

public class CreateGehegeDialog extends JDialog {

    public CreateGehegeDialog(JFrame parent, TierparkController controller, GehegePanel panel) {
        super(parent, "Gehege erstellen", true);

        setSize(300, 250);
        setLayout(new GridLayout(4, 2));

        JComboBox<String> typ = new JComboBox<>(new String[]{"Savanne", "Dschungel"});
        JTextField max = new JTextField();
        JTextField ft = new JTextField();

        add(new JLabel("Typ:"));
        add(typ);

        add(new JLabel("Max Tiere:"));
        add(max);

        add(new JLabel("Fütterungszeit:"));
        add(ft);

        JButton btn = new JButton("Erstellen");
        btn.addActionListener(e -> {
            try {
                String selectedType = (String) typ.getSelectedItem();
                int maxTiere = Integer.parseInt(max.getText().trim());
                String fT = ft.getText().trim();

                Gehege g;
                if ("Savanne".equals(selectedType)) {
                    g = new Savannengehege(maxTiere, fT);
                } else {
                    g = new Dschungelgehege(maxTiere, fT);
                }

                controller.addGehege(g);
                panel.refresh();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Bitte eine gültige Zahl für Max Tiere eingeben.");
            }
        });

        add(new JLabel());
        add(btn);

        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
