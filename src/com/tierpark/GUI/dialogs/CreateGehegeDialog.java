package GUI.dialogs;

/*
 * Dialog zum Erstellen eines neuen Geheges.
 * Der Benutzer wählt Typ, maximale Kapazität und Fütterungszeit aus.
 */

import GUI.controller.TierparkController;
import GUI.panels.GehegePanel;
import gehege.Dschungelgehege;
import gehege.Gehege;
import gehege.Savannengehege;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreateGehegeDialog extends JDialog {

    public CreateGehegeDialog(JFrame parent, TierparkController controller, GehegePanel panel) {
        super(parent, "Gehege erstellen", true);

        setSize(300, 250);
        setLayout(new GridLayout(4, 2));

        JComboBox<String> typ = new JComboBox<>(new String[]{"Savanne", "Dschungelgehege", "Wassergehege", "Eisgehege", "Waldgehege"});
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
                try {
                    DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime.parse(fT, DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Bitte Fütterungszeit im Format SS:MM eingeben.");
                    return;
                }

                Gehege g;
                switch (selectedType) {
                    case "Savanne":
                        g = new Savannengehege(maxTiere, fT);
                        break;
                    case "Dschungelgehege":
                        g = new Dschungelgehege(maxTiere, fT);
                        break;
                    case "Wassergehege":
                        g = new gehege.Wassergehege(0, maxTiere, fT);
                        break;
                    case "Eisgehege":
                        g = new gehege.Eisgehege(0, maxTiere, fT);
                        break;
                    case "Waldgehege":
                        g = new gehege.Waldgehege(0, maxTiere, fT);
                        break;
                    default:
                        g = new Savannengehege(maxTiere, fT);
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
        setLocation(parent.getX() + 40, parent.getY() + 40);
        setVisible(true);
    }
}
