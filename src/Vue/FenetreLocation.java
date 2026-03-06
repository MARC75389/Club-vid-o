package Vue;

import Dao.LocationDAO;
import modele.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class FenetreLocation extends JFrame {

    private LocationDAO locationDAO = new LocationDAO();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtIdAbonne, txtIdCassette;

    public FenetreLocation() {
        setTitle("Gestion des Locations");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulaire
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 5, 5));
        txtIdAbonne = new JTextField();
        txtIdCassette = new JTextField();

        panelForm.add(new JLabel("ID Abonné :")); panelForm.add(txtIdAbonne);
        panelForm.add(new JLabel("ID Cassette :")); panelForm.add(txtIdCassette);

        // Boutons
        JPanel panelBoutons = new JPanel(new FlowLayout());
        JButton btnLouer = new JButton("Louer");
        JButton btnRetourner = new JButton("Retourner");
        JButton btnActualiser = new JButton("Actualiser");

        btnLouer.setBackground(new Color(46, 204, 113));
        btnLouer.setForeground(Color.WHITE);
        btnRetourner.setBackground(new Color(231, 76, 60));
        btnRetourner.setForeground(Color.WHITE);
        btnActualiser.setBackground(new Color(52, 152, 219));
        btnActualiser.setForeground(Color.WHITE);

        panelBoutons.add(btnLouer);
        panelBoutons.add(btnRetourner);
        panelBoutons.add(btnActualiser);

        JPanel panelHaut = new JPanel(new BorderLayout());
        panelHaut.add(panelForm, BorderLayout.CENTER);
        panelHaut.add(panelBoutons, BorderLayout.SOUTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID Abonné", "ID Cassette", "Date Location"}, 0);
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
                txtIdAbonne.setText(tableModel.getValueAt(row, 0).toString());
                txtIdCassette.setText(tableModel.getValueAt(row, 1).toString());
            }
        });

        // Actions
        btnLouer.addActionListener(e -> {
            try {
                int idAbonne = Integer.parseInt(txtIdAbonne.getText().trim());
                int idCassette = Integer.parseInt(txtIdCassette.getText().trim());
                locationDAO.louer(new Location(idAbonne, idCassette, new Date()));
                chargerDonnees();
                viderChamps();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs ID !");
            }
        });

        btnRetourner.addActionListener(e -> {
            try {
                int idAbonne = Integer.parseInt(txtIdAbonne.getText().trim());
                int idCassette = Integer.parseInt(txtIdCassette.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmer le retour ?");
                if (confirm == JOptionPane.YES_OPTION) {
                    locationDAO.retourner(idAbonne, idCassette);
                    chargerDonnees();
                    viderChamps();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs ID !");
            }
        });

        btnActualiser.addActionListener(e -> chargerDonnees());

        setVisible(true);
    }

    private void chargerDonnees() {
        tableModel.setRowCount(0);
        List<Location> liste = locationDAO.getTout();
        for (Location l : liste) {
            tableModel.addRow(new Object[]{
                l.getNumAbonne(), l.getNumCassette(), l.getDateLocation()
            });
        }
    }

    private void viderChamps() {
        txtIdAbonne.setText("");
        txtIdCassette.setText("");
    }
}