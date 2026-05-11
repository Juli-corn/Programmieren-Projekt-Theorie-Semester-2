package app;


/*
ACHTUNG ACHTUNG! Dies ist kein fertiges Produkt, wir müssen das noch auf unseren Usecase anpassen, aber so als grobes Konstrukt macht mir das mein Leben deutlich einfacher.
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import gehege.Dschungelgehege;
import gehege.Gehege;
import gehege.Savannengehege;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    private List<Gehege> gehegeListe = new ArrayList<>();

    private void openGehegeWindow() {
        JFrame frame = new JFrame("Gehege");
        frame.setSize(700, 350);
        frame.setLocationRelativeTo(this);
        frame.setLayout(new BorderLayout());

        String[] columns = {"Typ", "T/T", "FT", "Tiere", "Bearbeiten"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4;
            }
        };

        refreshTable(model);

        // Button in Tabelle (Tiere-Spalte)
        table.getColumn("Tiere").setCellRenderer(new ButtonRenderer());
        table.getColumn("Tiere").setCellEditor(new ButtonEditor(new JCheckBox()));

        // Bearbeiten-Button
        table.getColumn("Bearbeiten").setCellRenderer(new ButtonRenderer());
        table.getColumn("Bearbeiten").setCellEditor(
            new EditButtonEditor(new JCheckBox(), gehegeListe, model, frame)
        );

        // Erstellen-Button
        JButton createButton = new JButton("Gehege erstellen");
        createButton.addActionListener(e -> openCreateDialog(frame, model));

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(createButton, BorderLayout.SOUTH);

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

    
    private void refreshTable(DefaultTableModel model) {
        model.setRowCount(0);

        for (Gehege g : gehegeListe) {
            model.addRow(new Object[]{
                    g.getTyp(),
                    g.getAnzahlUntergebrachteTiere() + "/" + g.getMaxTiere(),
                    g.getFuetterungszeit(),
                    "Bearbeiten"
            });
        }
    }

    
    private void openCreateDialog(JFrame parent, DefaultTableModel model) {
        JDialog dialog = new JDialog(parent, "Gehege erstellen", true);
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(4, 2));

        JComboBox<String> typBox = new JComboBox<>(new String[]{"Savanne", "Dschungel"});
        JTextField maxTiereField = new JTextField();
        JTextField fuetterungField = new JTextField();

        dialog.add(new JLabel("Typ:"));
        dialog.add(typBox);

        dialog.add(new JLabel("Max Tiere:"));
        dialog.add(maxTiereField);

        dialog.add(new JLabel("Fütterungszeit:"));
        dialog.add(fuetterungField);

        JButton createBtn = new JButton("Erstellen");

        createBtn.addActionListener(e -> {
            String typ = (String) typBox.getSelectedItem();
            int max = Integer.parseInt(maxTiereField.getText());
            String ft = fuetterungField.getText();

            Gehege g;

            switch (typ) {
                case "Savanne":
                    g = new Savannengehege(max, ft);
                    break;
                case "Dschungel":
                    g = new Dschungelgehege(max, ft);
                    break;
                default:
                    return;
            }

            gehegeListe.add(g);
            refreshTable(model);

            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(createBtn);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    
    private void openEditDialog(Gehege g, JFrame parent, DefaultTableModel model) {
        JDialog dialog = new JDialog(parent, "Gehege bearbeiten", true);
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(4, 2));

        JTextField maxTiereField = new JTextField(String.valueOf(g.getMaxTiere()));
        JTextField fuetterungField = new JTextField(g.getFuetterungszeit());

        dialog.add(new JLabel("Typ:"));
        dialog.add(new JLabel(g.getTyp()));  // nicht änderbar

        dialog.add(new JLabel("Max Tiere:"));
        dialog.add(maxTiereField);

        dialog.add(new JLabel("Fütterungszeit:"));
        dialog.add(fuetterungField);

        JButton saveBtn = new JButton("Speichern");

        saveBtn.addActionListener(e -> {
            try {
                int max = Integer.parseInt(maxTiereField.getText());
                String ft = fuetterungField.getText();

                g.setMaxTiere(max);                 // oder Setter benutzen!
                g.setFuetterungszeit(ft);           // dito

                refreshTable(model);
                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Bitte gültige Zahl eingeben!");
            }
        });

        dialog.add(new JLabel());
        dialog.add(saveBtn);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
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

    
    class EditButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int row;
        private List<Gehege> gehegeListe;
        private DefaultTableModel model;
        private JFrame parent;

        public EditButtonEditor(JCheckBox checkBox, List<Gehege> liste,
                                DefaultTableModel model, JFrame parent) {
            super(checkBox);
            this.gehegeListe = liste;
            this.model = model;
            this.parent = parent;

            button = new JButton("Bearbeiten");

            button.addActionListener(e -> {
                Gehege g = gehegeListe.get(row);
                openEditDialog(g, parent, model);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                    boolean isSelected, int row, int column) {
            this.row = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Bearbeiten";
        }
    }


}


