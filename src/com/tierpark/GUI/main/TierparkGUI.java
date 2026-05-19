package src.com.tierpark.GUI.main;

/*
 * Hauptfenster der Tierpark-Anwendung.
 * Hier startet die GUI, zeigt Übersichtsinformationen an
 * und öffnet die Unterfenster für Gehege, Personal, Tiere und Futterlager.
 */

import src.com.tierpark.GUI.controller.TierparkController;
import src.com.tierpark.GUI.panels.FutterlagerPanel;
import src.com.tierpark.GUI.panels.GehegePanel;
import src.com.tierpark.GUI.panels.PflegerPanel;
import src.com.tierpark.GUI.panels.TierePanel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TierparkGUI extends JFrame {

    private TierparkController controller;
    private final JLabel timeLabel;
    private final JLabel animalsLabel;
    private final JLabel staffLabel;
    private boolean shownUnassignedWarning = false;

    // Hauptfenster der Anwendung. Stellt die Übersicht und Navigationsschaltflächen bereit.
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
        staffLabel = new JLabel();

        infoPanel.add(timeLabel);
        infoPanel.add(animalsLabel);
        infoPanel.add(staffLabel);

        add(infoPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton gehegeBtn = new JButton("Gehege");
        JButton pflegerBtn = new JButton("Pfleger");
        JButton tierBtn = new JButton("Tiere");
        JButton futterlagerBtn = new JButton("Futterlager");

        gehegeBtn.addActionListener(e -> new GehegePanel(this, controller));
        pflegerBtn.addActionListener(e -> new PflegerPanel(this, controller));
        tierBtn.addActionListener(e -> new TierePanel(this, controller));
        futterlagerBtn.addActionListener(e -> new FutterlagerPanel(this, controller));
        buttonPanel.add(gehegeBtn);
        buttonPanel.add(pflegerBtn);
        buttonPanel.add(tierBtn);
        buttonPanel.add(futterlagerBtn);

        add(buttonPanel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, e -> updateLabels());
        timer.start();
        updateLabels();
    }

    public void setController(TierparkController controller) {
        this.controller = controller;
        updateLabels();
    }

    // Aktualisiert die Anzeige im Hauptfenster und verarbeitet dabei auch kranke Tiere.
    private void updateLabels() {
        if (controller != null) {
            String healedMessage = controller.processPendingKrank();
            if (!healedMessage.isBlank()) {
                JOptionPane.showMessageDialog(this, healedMessage, "Kranke Tiere geheilt", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText("Uhrzeit: " + sdf.format(new Date()));
        animalsLabel.setText("Anzahl Tiere: " + controller.getTierListe().size());
        staffLabel.setText("Pfleger/Ärzte: " + controller.getPersonalListe().size() + " (verfügbar: " + controller.getAvailablePersonalCount() + ")");
        // Warnung, falls Tiere ohne Gehege existieren (einmalig)
        long unassigned = controller.getTierListe().stream().filter(t -> !t.getInGehege()).count();
        if (unassigned > 0 && !shownUnassignedWarning) {
            shownUnassignedWarning = true;
            JOptionPane.showMessageDialog(this, "Es gibt " + unassigned + " Tiere ohne zugewiesenes Gehege.");
        }
    }
}
