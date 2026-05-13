package GUI.main;

import GUI.controller.TierparkController;
import GUI.panels.GehegePanel;
import GUI.panels.PflegerPanel;
import GUI.panels.TierePanel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TierparkGUI extends JFrame {

    private final TierparkController controller;
    private final JLabel timeLabel;
    private final JLabel animalsLabel;
    private final JLabel staffLabel;

    public TierparkGUI() {
        this.controller = new TierparkController();

        setTitle("Tierpark");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        timeLabel = new JLabel();
        animalsLabel = new JLabel();
        staffLabel = new JLabel("Pfleger/Ärzte: 10");

        infoPanel.add(timeLabel);
        infoPanel.add(animalsLabel);
        infoPanel.add(staffLabel);

        add(infoPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton gehegeBtn = new JButton("Gehege");
        JButton pflegerBtn = new JButton("Pfleger");
        JButton tierBtn = new JButton("Tiere");

        gehegeBtn.addActionListener(e -> new GehegePanel(this, controller));
        pflegerBtn.addActionListener(e -> new PflegerPanel(this));
        tierBtn.addActionListener(e -> new TierePanel(this, controller));

        buttonPanel.add(gehegeBtn);
        buttonPanel.add(pflegerBtn);
        buttonPanel.add(tierBtn);

        add(buttonPanel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, e -> updateLabels());
        timer.start();
        updateLabels();
    }

    private void updateLabels() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText("Uhrzeit: " + sdf.format(new Date()));
        animalsLabel.setText("Anzahl Tiere: " + controller.getTierListe().size());
    }
}
