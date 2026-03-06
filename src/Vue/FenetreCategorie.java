package Vue;

import Dao.CategorieDAO;
import modele.Categorie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FenetreCategorie extends JFrame {

    private CategorieDAO categorieDAO = new CategorieDAO();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtLibelle;

    public FenetreCategorie() {
        setTitle("Gestion des Catégories");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulaire
        JPanel panelForm = new JPanel(new FlowLayout());
        panelForm.add(new JLabel("Libellé :"));
        txtLibelle = new JTextField(20);
        panelForm.add(txtLibelle);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        btnAjouter.setBackground(new Color(46, 204, 113));
        btnAjouter.setForeground(Color.WHITE);
        btnModifier.setBackground(new Color(52, 152, 219));
        btnModifier.setForeground(Color.WHITE);
        btnSupprimer.setBackground(new Color(231, 76, 60));
        btnSupprimer.setForeground(Color.WHITE);

        panelForm.add(btnAjouter);
        panelForm.add(btnModifier);
        panelForm.add(btnSupprimer);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Libellé"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(panelForm, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        chargerDonnees();

        // Sélection dans la table
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                txtLibelle.setText((String) tableModel.getValueAt(row, 1));
            }
        });

        // Actions
        btnAjouter.addActionListener(e -> {
            String libelle = txtLibelle.getText().trim();
            if (!libelle.isEmpty()) {
                categorieDAO.ajouter(new Categorie(0, libelle));
                chargerDonnees();
                txtLibelle.setText("");
            }
        });

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = (int) tableModel.getValueAt(row, 0);
                String libelle = txtLibelle.getText().trim();
                categorieDAO.modifier(new Categorie(id, libelle));
                chargerDonnees();
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une catégorie !");
            }
        });

        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = (int) tableModel.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?");
                if (confirm == JOptionPane.YES_OPTION) {
                    categorieDAO.supprimer(id);
                    chargerDonnees();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une catégorie !");
            }
        });

        setVisible(true);
    }

    private void chargerDonnees() {
        tableModel.setRowCount(0);
        List<Categorie> liste = categorieDAO.getTout();
        for (Categorie c : liste) {
            tableModel.addRow(new Object[]{c.getNumCategorie(), c.getLibelleCategorie()});
        }
    }
}