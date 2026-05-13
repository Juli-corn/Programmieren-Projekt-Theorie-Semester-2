package GUI;


/*
ACHTUNG ACHTUNG! Dies ist kein fertiges Produkt, wir müssen das noch auf unseren Usecase anpassen, aber so als grobes Konstrukt macht mir das mein Leben deutlich einfacher.
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import gehege.Dschungelgehege;
import gehege.Gehege;
import gehege.Savannengehege;
import tier.Tier;
import tier.tiere.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TierparkGUI1 extends JFrame {

    private JLabel timeLabel;

    // Mainscreen
    public TierparkGUI1() {
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
        JButton tierBtn = new JButton("Tiere");

        gehegeBtn.addActionListener(e -> openGehegeWindow());
        pflegerBtn.addActionListener(e -> openPflegerWindow());
        tierBtn.addActionListener(e -> openTiereWindow());

        buttonPanel.add(gehegeBtn);
        buttonPanel.add(pflegerBtn);
        buttonPanel.add(tierBtn);

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

        refreshGehege(model);

        // Button in Tabelle (Tiere-Spalte)
        table.getColumn("Tiere").setCellRenderer(new ButtonRenderer());
        table.getColumn("Tiere").setCellEditor(new ButtonEditor(new JCheckBox()));

        // Bearbeiten-Button
        table.getColumn("Bearbeiten").setCellRenderer(new ButtonRenderer());
        table.getColumn("Bearbeiten").setCellEditor(
            new EditButtonEditorG(new JCheckBox(), gehegeListe, model, frame)
        );

        // Erstellen-Button
        JButton createButton = new JButton("Gehege erstellen");
        createButton.addActionListener(e -> openCreateDialogG(frame, model));

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

    // Fenster für Tierverwaltung
    private List<Tier> tierListe = new ArrayList<>();
    private void openTiereWindow() {
        JFrame frame =new JFrame("Tiere");
        frame.setSize(700, 250);
        frame.setLocationRelativeTo(this);
        frame.setLayout(new BorderLayout());

        String[] columns = {"Name", "Alter", "Art", "Lieblingsfutter", "Futtermenge", "Gehegetyp"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model) {

        };

        refreshTiere(model);

        JButton createButton = new JButton("Tier erstellen");
        createButton.addActionListener(e -> openCreateDialogT(frame, model));

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(createButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Fenster für Tiere (aus Gehege-Tabelle)
    private List<Tier> tierGehegeListe = new ArrayList<>();
    private void openTiereWindowG(Gehege g) {
        JFrame frame = new JFrame("Tiere in Gehege");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(this);
        frame.setLayout(new BorderLayout());

        String[] columns = {"Name", "Alter", "Art", "Futterverbrauch"};

        DefaultTableModel gehegeModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(gehegeModel);
        refreshTierGehegeListe(gehegeModel);

        frame.add(new JScrollPane(table));

        JButton addButton = new JButton("Tiere hinzufügen");
        addButton.addActionListener(e -> openAddDialogT(g, gehegeModel));

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(addButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Gehege Tabelle refreshen
    private void refreshGehege(DefaultTableModel model) {
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

    //Tiere In Gehege Liste refreshen
    private void refreshTierGehegeListe(DefaultTableModel model) {
        model.setRowCount(0);

        for (Tier t : tierGehegeListe) {
            model.addRow(new Object[]{
                t.getName(),
                t.getAlter(),
                t.getArt(),
                t.getFuttermenge()
            });
        }
    }

    //Tier Tabelle refreshen
    private void refreshTiere(DefaultTableModel model) {
        model.setRowCount(0);

        for (Tier t : tierListe) {
            model.addRow(new Object[]{
                t.getName(),
                t.getAlter(),
                t.getArt(),
                t.getLieblingsfutter(),
                t.getFuttermenge(),
                t.getGehegeTyp()
            });
        }
    }

    // Dialog für Gehge Erstellung 
    private void openCreateDialogG(JFrame parent, DefaultTableModel model) {
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
            refreshGehege(model);

            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(createBtn);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    // Dialog für Tierertellung 
    private void openCreateDialogT(JFrame parent, DefaultTableModel model) {
        JDialog dialog = new JDialog(parent, "Tier erstellen", true);
        dialog.setSize(500, 250);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Ameisenbär", "Axolotl", "Braunbär", "Capybara", "Erdmännchen", "Faultier",
        "Fennek", "Krokodil", "Nacktmull", "Pinguin", "Quokka", "Olivgrüne-Bastardschildkröte", "Seelöwe", "Stinktier", "Warzenschwein"});
        JTextField foodField = new JTextField();

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);

        dialog.add(new JLabel("Alter:"));
        dialog.add(ageField);

        dialog.add(new JLabel("Art:"));
        dialog.add(typeBox);

        dialog.add(new JLabel("Nahrung/Fütterung (kg):"));
        dialog.add(foodField);

        JButton createBtn = new JButton("Erstellen");

        createBtn.addActionListener(e -> {

            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String typ = (String) typeBox.getSelectedItem();
            int food = Integer.parseInt(foodField.getText());

            Tier t;

            switch (typ) {
                case "Ameisenbär":
                    t = new Ameisenbär(name, age, food);
                    break;
                case "Axolotl":
                    t = new Axolotl(name, age, food);
                    break;
                case "Braunbär":
                    t = new Braunbaer(name, age, food);
                    break;
                case "Capybara":
                    t = new Capybara(name, age, food);
                    break;
                case "Erdmännchen":
                    t = new Erdmännchen(name, age, food);
                    break;
                case "Faultier":
                    t = new Faultier(name, age, food);
                    break;
                case "Fennek":
                    t = new Fennek(name, age, food);
                    break;
                case "Krokodil":
                    t = new Krokodil(name, age, food);
                    break;
                case "Nacktmull":
                    t = new Nacktmull(name, age, food);
                    break;
                case "Pinguin":
                    t = new Pinguin(name, age, food);
                    break;
                case "Quokka":
                    t = new Quokka(name, age, food);
                    break;
                case "Olivgrüne-Bastardschildkröte":
                    t = new Schildkröte(name, age, food);
                    break;
                case "Seelöwe":
                    t = new Seelöwe(name, age, food);
                    break;
                case "Stinktier":
                    t = new Stinktier(name, age, food);
                    break;
                case "Warzenschwein":
                    t = new Warzenschwein(name, age, food);
                    break;
                default:
                    return;
            }

            tierListe.add(t);
            refreshTiere(model);

            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(createBtn);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    // Tier zu Gehege hinzufügen
    private void openAddDialogT(Gehege g, DefaultTableModel gehegeModel) {
        JFrame frame = new JFrame("Tiere zum Hinzufügen");
        frame.setSize(400, 250);

        String[] columns = {"Art", "Futterverbrauch", "Name", "Alter", "Hinzufügen"};

        DefaultTableModel addModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(addModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };


        refreshTierToAddListe(addModel, g);

        table.getColumn("Hinzufügen").setCellRenderer(new ButtonRenderer());
        table.getColumn("Hinzufügen").setCellEditor(new AddButtonEditorT(new JCheckBox(), g, addModel, gehegeModel));
        
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
        
    }

    // Zeigt Tiere, die in dieses Gehege geschoben werden können.
    private void refreshTierToAddListe(DefaultTableModel model, Gehege g) {
        model.setRowCount(0);

        for (Tier t : tierListe) {
            if (t.getGehegeTyp().equals(g.getTyp()) && t.getInGehege() == false) {
                model.addRow(new Object[]{
                    t.getArt(),
                    t.getFuttermenge(),
                    t.getName(),
                    t.getAlter(),
                    "Hinzufügen"
                });
            }
        }
    }
    
    
    // Dialog für Gehege bearbeiten
    private void openEditDialogG(Gehege g, JFrame parent, DefaultTableModel model) {
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

                g.setMaxTiere(max);                 
                g.setFuetterungszeit(ft);           

                refreshGehege(model);
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
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Öffnen");

            button.addActionListener(e -> {
                Gehege g = gehegeListe.get(row);
                openTiereWindowG(g);
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            return button;
        }

        public Object getCellEditorValue() {
            return "Öffnen";
        }

    }

    // Button Editor für Gehege bearbeitung 
    class EditButtonEditorG extends DefaultCellEditor {
        private JButton button;
        private int row;
        private List<Gehege> gehegeListe;
        private DefaultTableModel model;
        private JFrame parent;

        public EditButtonEditorG(JCheckBox checkBox, List<Gehege> liste,
                                DefaultTableModel model, JFrame parent) {
            super(checkBox);
            this.gehegeListe = liste;
            this.model = model;
            this.parent = parent;

            button = new JButton("Bearbeiten");

            button.addActionListener(e -> {
                Gehege g = gehegeListe.get(row);
                openEditDialogG(g, parent, model);
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

    class AddButtonEditorT extends DefaultCellEditor {
        
        private JButton button;
        private Gehege gehege;
        private Tier currentTier;

        
        private DefaultTableModel addModel;
        private DefaultTableModel gehegeModel;

        
        public AddButtonEditorT(JCheckBox checkBox, Gehege g, DefaultTableModel addModel, DefaultTableModel gehegeModel) {
            super(checkBox);
            this.gehege = g;
            this.addModel = addModel;
            this.gehegeModel = gehegeModel;


            button = new JButton("Hinzufügen");

            button.addActionListener(e -> {
                fireEditingStopped();
                
                tierGehegeListe.add(currentTier);
                gehege.tierHinzufuegen(currentTier);
                currentTier.setInGehege(true);

                refreshTierGehegeListe(gehegeModel);
                refreshTierToAddListe(addModel, g);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            
            String name = table.getValueAt(row, 2).toString(); // Name-Spalte

                for (Tier t : tierListe) {

                    if (t.getName().equals(name) && !t.getInGehege()) {
                        currentTier = t;
                        break;
                    }
                }

            return button;

        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }

        @Override
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

    }

}


