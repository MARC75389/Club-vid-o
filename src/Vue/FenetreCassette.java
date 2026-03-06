package Vue;

import Dao.CassetteDAO;
import modele.Cassette;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class FenetreCassette extends JFrame {

    private CassetteDAO cassetteDAO = new CassetteDAO();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtTitre, txtAuteur, txtDuree, txtPrix, txtCategorie;

    public FenetreCassette() {
        setTitle("Gestion des Cassettes");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulaire
        JPanel panelForm = new JPanel(new GridLayout(3, 4, 5, 5));
        txtTitre = new JTextField();
        txtAuteur = new JTextField();
        txtDuree = new JTextField();
        txtPrix = new JTextField();
        txtCategorie = new JTextField();

        panelForm.add(new JLabel("Titre :")); panelForm.add(txtTitre);
        panelForm.add(new JLabel("Auteur :")); panelForm.add(txtAuteur);
        panelForm.add(new JLabel("Durée (min) :")); panelForm.add(txtDuree);
        panelForm.add(new JLabel("Prix :")); panelForm.add(txtPrix);
        panelForm.add(new JLabel("ID Catégorie :")); panelForm.add(txtCategorie);

        // Boutons
        JPanel panelBoutons = new JPanel(new FlowLayout());
        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        btnAjouter.setBackground(new Color(46, 204, 113));
        btnAjouter.setForeground(Color.WHITE);
        btnModifier.setBackground(new Color(52, 152, 219));
        btnModifier.setForeground(Color.WHITE);
        btnSupprimer.setBackground(new Color(231, 76, 60));
        btnSupprimer.setForeground(Color.WHITE);

        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnModifier);
        panelBoutons.add(btnSupprimer);

        JPanel panelHaut = new JPanel(new BorderLayout());
        panelHaut.add(panelForm, BorderLayout.CENTER);
        panelHaut.add(panelBoutons, BorderLayout.SOUTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Titre", "Auteur", "Durée", "Prix", "Catégorie"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(panelHaut, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        chargerDonnees();

        // Sélection dans la table
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                txtTitre.setText((String) tableModel.getValueAt(row, 1));
                txtAuteur.setText((String) tableModel.getValueAt(row, 2));
                txtDuree.setText(tableModel.getValueAt(row, 3).toString());
                txtPrix.setText(tableModel.getValueAt(row, 4).toString());
                txtCategorie.setText(tableModel.getValueAt(row, 5).toString());
            }
        });

        // Actions
        btnAjouter.addActionListener(e -> {
            try {
                Cassette c = new Cassette();
                c.setTitre(txtTitre.getText().trim());
                c.setAuteur(txtAuteur.getText().trim());
                c.setDuree(Integer.parseInt(txtDuree.getText().trim()));
                c.setPrix(Double.parseDouble(txtPrix.getText().trim()));
                c.setNumCategorie(Integer.parseInt(txtCategorie.getText().trim()));
                c.setDateAchat(new Date());
                cassetteDAO.ajouter(c);
                chargerDonnees();
                viderChamps();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs numériques !");
            }
        });

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                try {
                    Cassette c = cassetteDAO.getParId((int) tableModel.getValueAt(row, 0));
                    c.setTitre(txtTitre.getText().trim());
                    c.setAuteur(txtAuteur.getText().trim());
                    c.setDuree(Integer.parseInt(txtDuree.getText().trim()));
                    c.setPrix(Double.parseDouble(txtPrix.getText().trim()));
                    c.setNumCategorie(Integer.parseInt(txtCategorie.getText().trim()));
                    cassetteDAO.modifier(c);
                    chargerDonnees();
                    viderChamps();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Vérifiez les champs numériques !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une cassette !");
            }
        });

        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?");
                if (confirm == JOptionPane.YES_OPTION) {
                    cassetteDAO.supprimer((int) tableModel.getValueAt(row, 0));
                    chargerDonnees();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une cassette !");
            }
        });

        setVisible(true);
    }

    private void chargerDonnees() {
        tableModel.setRowCount(0);
        List<Cassette> liste = cassetteDAO.getTout();
        for (Cassette c : liste) {
            tableModel.addRow(new Object[]{
                c.getNumCassette(), c.getTitre(), c.getAuteur(),
                c.getDuree(), c.getPrix(), c.getNumCategorie()
            });
        }
    }

    private void viderChamps() {
        txtTitre.setText("");
        txtAuteur.setText("");
        txtDuree.setText("");
        txtPrix.setText("");
        txtCategorie.setText("");
    }
}