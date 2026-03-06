package Vue;

import Dao.AbonneDAO;
import modele.Abonne;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class FenetreAbonne extends JFrame {

    private AbonneDAO abonneDAO = new AbonneDAO();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNom, txtAdresse;

    public FenetreAbonne() {
        setTitle("Gestion des Abonnés");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulaire
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 5, 5));
        txtNom = new JTextField();
        txtAdresse = new JTextField();

        panelForm.add(new JLabel("Nom :")); panelForm.add(txtNom);
        panelForm.add(new JLabel("Adresse :")); panelForm.add(txtAdresse);

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
        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Adresse", "Date Abonnement", "Nb Locations"}, 0);
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
                txtNom.setText((String) tableModel.getValueAt(row, 1));
                txtAdresse.setText((String) tableModel.getValueAt(row, 2));
            }
        });

        // Actions
        btnAjouter.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String adresse = txtAdresse.getText().trim();
            if (!nom.isEmpty() && !adresse.isEmpty()) {
                Abonne a = new Abonne();
                a.setNomAbonne(nom);
                a.setAdresseAbonne(adresse);
                a.setDateAbonnement(new Date());
                a.setDateEntree(new Date());
                a.setNombreLocation(0);
                abonneDAO.ajouter(a);
                chargerDonnees();
                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, "Remplissez tous les champs !");
            }
        });

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Abonne a = abonneDAO.getParId((int) tableModel.getValueAt(row, 0));
                a.setNomAbonne(txtNom.getText().trim());
                a.setAdresseAbonne(txtAdresse.getText().trim());
                abonneDAO.modifier(a);
                chargerDonnees();
                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un abonné !");
            }
        });

        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?");
                if (confirm == JOptionPane.YES_OPTION) {
                    abonneDAO.supprimer((int) tableModel.getValueAt(row, 0));
                    chargerDonnees();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un abonné !");
            }
        });

        setVisible(true);
    }

    private void chargerDonnees() {
        tableModel.setRowCount(0);
        List<Abonne> liste = abonneDAO.getTout();
        for (Abonne a : liste) {
            tableModel.addRow(new Object[]{
                a.getNumAbonne(), a.getNomAbonne(), a.getAdresseAbonne(),
                a.getDateAbonnement(), a.getNombreLocation()
            });
        }
    }

    private void viderChamps() {
        txtNom.setText("");
        txtAdresse.setText("");
    }
}