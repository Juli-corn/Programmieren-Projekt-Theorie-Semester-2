package app;


/*
ACHTUNG ACHTUNG! Dies ist kein fertiges Produkt, wir müssen das noch auf unseren Usecase anpassen, aber so als grobes Konstrukt macht mir das mein Leben deutlich einfacher.
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TierparkGUI extends JFrame {

        private JLabel timeLabel;

    public TierparkGUI() {
        setTitle("Tierpark");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel (Übersicht)
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));

         timeLabel = new JLabel();
        JLabel animalsLabel = new JLabel("Anzahl Tiere: 42");
        JLabel staffLabel = new JLabel("Pfleger/Ärzte: 10");

        infoPanel.add(timeLabel);
        infoPanel.add(animalsLabel);
        infoPanel.add(staffLabel);

        add(infoPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton gehegeBtn = new JButton("Gehege");
        JButton pflegerBtn = new JButton("Pfleger");

        gehegeBtn.addActionListener(e -> openGehegeWindow());
        pflegerBtn.addActionListener(e -> openPflegerWindow());

        buttonPanel.add(gehegeBtn);
        buttonPanel.add(pflegerBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Uhrzeit aktualisieren
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText("Uhrzeit: " + sdf.format(new Date()));
    }

    // Gehege Fenster
    private void openGehegeWindow() {
        JFrame frame = new JFrame("Gehege");
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(this);

        String[] columns = {"Typ", "T/T", "K", "FT", "Tiere"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        model.addRow(new Object[]{"Savanne", "5/10", 3, "12:00", "Tiere"});
        model.addRow(new Object[]{"Wald", "2/8", 2, "09:00", "Tiere"});

        JTable table = new JTable(model);

        // Button in Tabelle (Tiere-Spalte)
        table.getColumn("Tiere").setCellRenderer(new ButtonRenderer());
        table.getColumn("Tiere").setCellEditor(new ButtonEditor(new JCheckBox()));

        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }

    // Pfleger Fenster
    private void openPflegerWindow() {
        JFrame frame = new JFrame("Pfleger");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(this);

        String[] columns = {"Name", "Art", "Schicht"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        model.addRow(new Object[]{"Max Mustermann", "Tierpfleger", "08:00 - 16:00"});
        model.addRow(new Object[]{"Anna Schmidt", "Tierarzt", "10:00 - 18:00"});

        JTable table = new JTable(model);

        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }

    // Fenster für Tiere (aus Gehege-Tabelle)
    private void openTiereWindow() {
        JFrame frame = new JFrame("Tiere");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(this);

        // Blanko Tabelle
        String[] columns = {"Spalte 1", "Spalte 2", "Spalte 3"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        frame.add(new JScrollPane(table));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TierparkGUI().setVisible(true));
    }

    // Renderer für Button
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setText("Öffnen");
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor für Button (Klick-Event)
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Öffnen");

            button.addActionListener((ActionEvent e) -> openTiereWindow());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            return button;
        }

        public Object getCellEditorValue() {
            return "Öffnen";
        }
    }

}


