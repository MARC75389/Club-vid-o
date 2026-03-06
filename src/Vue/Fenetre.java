package Vue;

import Connexion.Connexion;
import Dao.CategorieDAO;
import Dao.CassetteDAO;
import Dao.AbonneDAO;
import Dao.LocationDAO;
import modele.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

public class Fenetre extends JFrame {

    private static final Color SIDEBAR_BG = new Color(15, 23, 42);
    private static final Color SIDEBAR_HOVER = new Color(30, 41, 59);
    private static final Color SIDEBAR_ACTIVE = new Color(59, 130, 246);
    private static final Color MAIN_BG = new Color(241, 245, 249);
    private static final Color CARD_BG = Color.WHITE;
    private static final Color HEADER_BG = new Color(15, 23, 42);
    private static final Color TEXT_LIGHT = new Color(248, 250, 252);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color ACCENT = new Color(59, 130, 246);
    private static final Color SUCCESS = new Color(34, 197, 94);
    private static final Color DANGER = new Color(239, 68, 68);
    private static final Color WARNING = new Color(234, 179, 8);

    private JPanel panelContenu;
    private String role;

    private CategorieDAO categorieDAO = new CategorieDAO();
    private CassetteDAO cassetteDAO = new CassetteDAO();
    private AbonneDAO abonneDAO = new AbonneDAO();
    private LocationDAO locationDAO = new LocationDAO();

    public Fenetre(String role) {
        this.role = role;
        setTitle("Club Vidéo — " + (role.equals("admin") ? "Administration" : "Espace Client"));
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(creerHeader(), BorderLayout.NORTH);
        add(creerSidebar(role), BorderLayout.WEST);

        panelContenu = new JPanel(new BorderLayout());
        panelContenu.setBackground(MAIN_BG);
        panelContenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        if (role.equals("admin")) {
            afficherDashboard();
        } else {
            afficherEspaceClient();
        }

        add(panelContenu, BorderLayout.CENTER);
        setVisible(true);
        toFront();
    }

    // ─── HEADER ───
    private JPanel creerHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_BG);
        header.setPreferredSize(new Dimension(1100, 55));
        header.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JLabel logo = new JLabel("🎬  CLUB VIDÉO");
        logo.setFont(new Font("Georgia", Font.BOLD, 20));
        logo.setForeground(TEXT_LIGHT);

        JPanel menuHaut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        menuHaut.setBackground(HEADER_BG);

        JLabel lblRole = new JLabel(role.equals("admin") ? "👤 Administrateur" : "🎭 Client");
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblRole.setForeground(new Color(148, 163, 184));

        JButton btnDeconnexion = creerBoutonMenu("🔓 Déconnexion", DANGER);
        btnDeconnexion.addActionListener(e -> deconnecter());

        menuHaut.add(lblRole);
        menuHaut.add(btnDeconnexion);

        header.add(logo, BorderLayout.WEST);
        header.add(menuHaut, BorderLayout.EAST);

        return header;
    }

    private JButton creerBoutonMenu(String texte, Color couleur) {
        JButton btn = new JButton(texte);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(couleur);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        return btn;
    }

    // ─── SIDEBAR ───
    private JPanel creerSidebar(String role) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(220, 700));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel lblMenu = new JLabel("  NAVIGATION");
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMenu.setForeground(new Color(100, 116, 139));
        lblMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblMenu.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
        sidebar.add(lblMenu);

        if (role.equals("admin")) {
            sidebar.add(creerItemSidebar("🏠  Dashboard", () -> afficherDashboard()));
            sidebar.add(creerItemSidebar("🎭  Catégories", () -> afficherCategories()));
            sidebar.add(creerItemSidebar("📼  Cassettes", () -> afficherCassettes()));
            sidebar.add(creerItemSidebar("👤  Abonnés", () -> afficherAbonnes()));
            sidebar.add(creerItemSidebar("📋  Locations", () -> afficherLocations()));
        } else {
            sidebar.add(creerItemSidebar("🎭  Catégories", () -> afficherCategoriesClient()));
            sidebar.add(creerItemSidebar("📼  Cassettes", () -> afficherEspaceClient()));
            sidebar.add(creerItemSidebar("📋  Louer une cassette", () -> afficherLocationClient()));
        }

        sidebar.add(Box.createVerticalGlue());

        JLabel version = new JLabel("  v1.0 — HIT-T");
        version.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        version.setForeground(new Color(71, 85, 105));
        sidebar.add(version);

        return sidebar;
    }

    private JPanel creerItemSidebar(String texte, Runnable action) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 12));
        item.setBackground(SIDEBAR_BG);
        item.setMaximumSize(new Dimension(220, 50));
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lbl = new JLabel(texte);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(148, 163, 184));
        item.add(lbl);

        item.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                item.setBackground(SIDEBAR_HOVER);
                lbl.setForeground(TEXT_LIGHT);
            }
            public void mouseExited(MouseEvent e) {
                item.setBackground(SIDEBAR_BG);
                lbl.setForeground(new Color(148, 163, 184));
            }
            public void mouseClicked(MouseEvent e) {
                item.setBackground(SIDEBAR_ACTIVE);
                lbl.setForeground(TEXT_LIGHT);
                action.run();
            }
        });

        return item;
    }

    // ─── DASHBOARD (ADMIN) ───
    private void afficherDashboard() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("Dashboard");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel cartes = new JPanel(new GridLayout(1, 4, 20, 0));
        cartes.setBackground(MAIN_BG);

        cartes.add(creerCarte("🎭 Catégories", String.valueOf(categorieDAO.getTout().size()), ACCENT));
        cartes.add(creerCarte("📼 Cassettes", String.valueOf(cassetteDAO.getTout().size()), SUCCESS));
        cartes.add(creerCarte("👤 Abonnés", String.valueOf(abonneDAO.getTout().size()), WARNING));
        cartes.add(creerCarte("📋 Locations", String.valueOf(locationDAO.getTout().size()), DANGER));

        panelContenu.add(cartes, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    private JPanel creerCarte(String titre, String valeur, Color couleur) {
        JPanel carte = new JPanel(new BorderLayout());
        carte.setBackground(CARD_BG);
        carte.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, couleur),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitre = new JLabel(titre);
        lblTitre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitre.setForeground(new Color(100, 116, 139));

        JLabel lblValeur = new JLabel(valeur);
        lblValeur.setFont(new Font("Georgia", Font.BOLD, 40));
        lblValeur.setForeground(couleur);

        carte.add(lblTitre, BorderLayout.NORTH);
        carte.add(lblValeur, BorderLayout.CENTER);
        return carte;
    }

    // ─── CATEGORIES (ADMIN) ───
    private void afficherCategories() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("Gestion des Catégories");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout(0, 15));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        form.setBackground(CARD_BG);
        JTextField txtLibelle = new JTextField(25);
        JButton btnAjouter = creerBoutonAction("+ Ajouter", SUCCESS);
        JButton btnModifier = creerBoutonAction("✏ Modifier", ACCENT);
        JButton btnSupprimer = creerBoutonAction("🗑 Supprimer", DANGER);

        form.add(new JLabel("Libellé :")); form.add(txtLibelle);
        form.add(btnAjouter); form.add(btnModifier); form.add(btnSupprimer);

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Libellé"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = creerTable(model);
        chargerCategories(model);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) txtLibelle.setText((String) model.getValueAt(row, 1));
        });

        btnAjouter.addActionListener(e -> {
            String lib = txtLibelle.getText().trim();
            if (!lib.isEmpty()) {
                categorieDAO.ajouter(new Categorie(0, lib));
                chargerCategories(model);
                txtLibelle.setText("");
            }
        });

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                categorieDAO.modifier(new Categorie((int) model.getValueAt(row, 0), txtLibelle.getText().trim()));
                chargerCategories(model);
            } else JOptionPane.showMessageDialog(this, "Sélectionnez une ligne !");
        });

        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Confirmer ?") == JOptionPane.YES_OPTION) {
                categorieDAO.supprimer((int) model.getValueAt(row, 0));
                chargerCategories(model);
            }
        });

        card.add(form, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        panelContenu.add(card, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    private void chargerCategories(DefaultTableModel model) {
        model.setRowCount(0);
        for (Categorie c : categorieDAO.getTout())
            model.addRow(new Object[]{c.getNumCategorie(), c.getLibelleCategorie()});
    }

    // ─── CASSETTES (ADMIN) ───
    private void afficherCassettes() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("Gestion des Cassettes");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout(0, 15));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new GridLayout(2, 6, 8, 8));
        form.setBackground(CARD_BG);

        JTextField txtTitre = new JTextField(); txtTitre.setToolTipText("Titre");
        JTextField txtAuteur = new JTextField(); txtAuteur.setToolTipText("Auteur");
        JTextField txtDuree = new JTextField(); txtDuree.setToolTipText("Durée (min)");
        JTextField txtPrix = new JTextField(); txtPrix.setToolTipText("Prix");
        JTextField txtCat = new JTextField(); txtCat.setToolTipText("ID Catégorie");

        form.add(new JLabel("Titre :")); form.add(txtTitre);
        form.add(new JLabel("Auteur :")); form.add(txtAuteur);
        form.add(new JLabel("Durée :")); form.add(txtDuree);
        form.add(new JLabel("Prix :")); form.add(txtPrix);
        form.add(new JLabel("ID Catégorie :")); form.add(txtCat);

        JPanel boutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        boutons.setBackground(CARD_BG);
        JButton btnAjouter = creerBoutonAction("+ Ajouter", SUCCESS);
        JButton btnModifier = creerBoutonAction("✏ Modifier", ACCENT);
        JButton btnSupprimer = creerBoutonAction("🗑 Supprimer", DANGER);
        boutons.add(btnAjouter); boutons.add(btnModifier); boutons.add(btnSupprimer);

        JPanel haut = new JPanel(new BorderLayout());
        haut.setBackground(CARD_BG);
        haut.add(form, BorderLayout.CENTER);
        haut.add(boutons, BorderLayout.SOUTH);

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Titre", "Auteur", "Durée", "Prix", "Catégorie"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = creerTable(model);
        chargerCassettes(model);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                txtTitre.setText(model.getValueAt(row, 1).toString());
                txtAuteur.setText(model.getValueAt(row, 2).toString());
                txtDuree.setText(model.getValueAt(row, 3).toString());
                txtPrix.setText(model.getValueAt(row, 4).toString());
                txtCat.setText(model.getValueAt(row, 5).toString());
            }
        });

        btnAjouter.addActionListener(e -> {
            try {
                Cassette c = new Cassette();
                c.setTitre(txtTitre.getText().trim());
                c.setAuteur(txtAuteur.getText().trim());
                c.setDuree(Integer.parseInt(txtDuree.getText().trim()));
                c.setPrix(Double.parseDouble(txtPrix.getText().trim()));
                c.setNumCategorie(Integer.parseInt(txtCat.getText().trim()));
                c.setDateAchat(new Date());
                cassetteDAO.ajouter(c);
                chargerCassettes(model);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs numériques !");
            }
        });

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                try {
                    Cassette c = cassetteDAO.getParId((int) model.getValueAt(row, 0));
                    c.setTitre(txtTitre.getText().trim());
                    c.setAuteur(txtAuteur.getText().trim());
                    c.setDuree(Integer.parseInt(txtDuree.getText().trim()));
                    c.setPrix(Double.parseDouble(txtPrix.getText().trim()));
                    c.setNumCategorie(Integer.parseInt(txtCat.getText().trim()));
                    cassetteDAO.modifier(c);
                    chargerCassettes(model);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Vérifiez les champs numériques !");
                }
            }
        });

        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Confirmer ?") == JOptionPane.YES_OPTION) {
                cassetteDAO.supprimer((int) model.getValueAt(row, 0));
                chargerCassettes(model);
            }
        });

        card.add(haut, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        panelContenu.add(card, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    private void chargerCassettes(DefaultTableModel model) {
        model.setRowCount(0);
        for (Cassette c : cassetteDAO.getTout())
            model.addRow(new Object[]{c.getNumCassette(), c.getTitre(), c.getAuteur(), c.getDuree(), c.getPrix(), c.getNumCategorie()});
    }

    // ─── ABONNES (ADMIN) ───
    private void afficherAbonnes() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("Gestion des Abonnés");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout(0, 15));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        form.setBackground(CARD_BG);
        JTextField txtNom = new JTextField(18);
        JTextField txtAdresse = new JTextField(22);

        form.add(new JLabel("Nom :")); form.add(txtNom);
        form.add(new JLabel("Adresse :")); form.add(txtAdresse);

        JButton btnAjouter = creerBoutonAction("+ Ajouter", SUCCESS);
        JButton btnModifier = creerBoutonAction("✏ Modifier", ACCENT);
        JButton btnSupprimer = creerBoutonAction("🗑 Supprimer", DANGER);
        form.add(btnAjouter); form.add(btnModifier); form.add(btnSupprimer);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Nom", "Adresse", "Date Abonnement", "Nb Locations"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = creerTable(model);
        chargerAbonnes(model);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                txtNom.setText(model.getValueAt(row, 1).toString());
                txtAdresse.setText(model.getValueAt(row, 2).toString());
            }
        });

        btnAjouter.addActionListener(e -> {
            if (!txtNom.getText().trim().isEmpty()) {
                Abonne a = new Abonne();
                a.setNomAbonne(txtNom.getText().trim());
                a.setAdresseAbonne(txtAdresse.getText().trim());
                a.setDateAbonnement(new Date());
                a.setDateEntree(new Date());
                a.setNombreLocation(0);
                abonneDAO.ajouter(a);
                chargerAbonnes(model);
                txtNom.setText(""); txtAdresse.setText("");
            }
        });

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Abonne a = abonneDAO.getParId((int) model.getValueAt(row, 0));
                a.setNomAbonne(txtNom.getText().trim());
                a.setAdresseAbonne(txtAdresse.getText().trim());
                abonneDAO.modifier(a);
                chargerAbonnes(model);
            }
        });

        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Confirmer ?") == JOptionPane.YES_OPTION) {
                abonneDAO.supprimer((int) model.getValueAt(row, 0));
                chargerAbonnes(model);
            }
        });

        card.add(form, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        panelContenu.add(card, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    private void chargerAbonnes(DefaultTableModel model) {
        model.setRowCount(0);
        for (Abonne a : abonneDAO.getTout())
            model.addRow(new Object[]{a.getNumAbonne(), a.getNomAbonne(), a.getAdresseAbonne(), a.getDateAbonnement(), a.getNombreLocation()});
    }

    // ─── LOCATIONS (ADMIN) ───
    private void afficherLocations() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("Gestion des Locations");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout(0, 15));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        form.setBackground(CARD_BG);
        JTextField txtAbonne = new JTextField(8);
        JTextField txtCassette = new JTextField(8);

        form.add(new JLabel("ID Abonné :")); form.add(txtAbonne);
        form.add(new JLabel("ID Cassette :")); form.add(txtCassette);

        JButton btnLouer = creerBoutonAction("📼 Louer", SUCCESS);
        JButton btnRetourner = creerBoutonAction("↩ Retourner", DANGER);
        JButton btnActualiser = creerBoutonAction("🔄 Actualiser", ACCENT);
        form.add(btnLouer); form.add(btnRetourner); form.add(btnActualiser);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID Abonné", "ID Cassette", "Date Location"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = creerTable(model);
        chargerLocations(model);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                txtAbonne.setText(model.getValueAt(row, 0).toString());
                txtCassette.setText(model.getValueAt(row, 1).toString());
            }
        });

        btnLouer.addActionListener(e -> {
            try {
                locationDAO.louer(new Location(
                    Integer.parseInt(txtAbonne.getText().trim()),
                    Integer.parseInt(txtCassette.getText().trim()),
                    new Date()));
                chargerLocations(model);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs ID !");
            }
        });

        btnRetourner.addActionListener(e -> {
            try {
                if (JOptionPane.showConfirmDialog(this, "Confirmer le retour ?") == JOptionPane.YES_OPTION) {
                    locationDAO.retourner(
                        Integer.parseInt(txtAbonne.getText().trim()),
                        Integer.parseInt(txtCassette.getText().trim()));
                    chargerLocations(model);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs ID !");
            }
        });

        btnActualiser.addActionListener(e -> chargerLocations(model));

        card.add(form, BorderLayout.NORTH);
        card.add(new JScrollPane(table), BorderLayout.CENTER);
        panelContenu.add(card, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    private void chargerLocations(DefaultTableModel model) {
        model.setRowCount(0);
        for (Location l : locationDAO.getTout())
            model.addRow(new Object[]{l.getNumAbonne(), l.getNumCassette(), l.getDateLocation()});
    }

    // ─── CATEGORIES (CLIENT) ───
    private void afficherCategoriesClient() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("📂 Catalogue — Catégories");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Catégorie"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = creerTable(model);

        for (Categorie c : categorieDAO.getTout())
            model.addRow(new Object[]{c.getNumCategorie(), c.getLibelleCategorie()});

        card.add(new JScrollPane(table), BorderLayout.CENTER);
        panelContenu.add(card, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    // ─── CASSETTES (CLIENT) ───
    private void afficherEspaceClient() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("📼 Catalogue — Cassettes disponibles");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Titre", "Auteur", "Durée (min)", "Prix", "Catégorie"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = creerTable(model);

        for (Cassette c : cassetteDAO.getTout())
            model.addRow(new Object[]{c.getNumCassette(), c.getTitre(), c.getAuteur(), c.getDuree(), c.getPrix(), c.getNumCategorie()});

        card.add(new JScrollPane(table), BorderLayout.CENTER);
        panelContenu.add(card, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    // ─── LOCATION (CLIENT) ───
    private void afficherLocationClient() {
        panelContenu.removeAll();

        JLabel titre = new JLabel("📋 Louer une Cassette");
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(TEXT_DARK);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelContenu.add(titre, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout(0, 15));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        form.setBackground(CARD_BG);

        JTextField txtAbonne = new JTextField(8);
        JTextField txtCassette = new JTextField(8);

        form.add(new JLabel("Mon ID Abonné :")); form.add(txtAbonne);
        form.add(new JLabel("ID Cassette :")); form.add(txtCassette);

        JButton btnLouer = creerBoutonAction("📼 Louer", SUCCESS);
        JButton btnRetourner = creerBoutonAction("↩ Retourner", DANGER);
        JButton btnVoirMesLoc = creerBoutonAction("🔍 Mes locations", ACCENT);
        form.add(btnLouer); form.add(btnRetourner); form.add(btnVoirMesLoc);

        JLabel info = new JLabel("⚠️  Maximum 3 cassettes en location simultanément.");
        info.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        info.setForeground(WARNING);

        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfo.setBackground(CARD_BG);
        panelInfo.add(info);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID Abonné", "ID Cassette", "Date Location"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = creerTable(model);

        btnVoirMesLoc.addActionListener(e -> {
            try {
                int idAbonne = Integer.parseInt(txtAbonne.getText().trim());
                model.setRowCount(0);
                for (Location l : locationDAO.getLocationsAbonne(idAbonne))
                    model.addRow(new Object[]{l.getNumAbonne(), l.getNumCassette(), l.getDateLocation()});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Entrez votre ID abonné !");
            }
        });

        btnLouer.addActionListener(e -> {
            try {
                int idAbonne = Integer.parseInt(txtAbonne.getText().trim());
                int idCassette = Integer.parseInt(txtCassette.getText().trim());
                locationDAO.louer(new Location(idAbonne, idCassette, new Date()));
                model.setRowCount(0);
                for (Location l : locationDAO.getLocationsAbonne(idAbonne))
                    model.addRow(new Object[]{l.getNumAbonne(), l.getNumCassette(), l.getDateLocation()});
                txtCassette.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs ID !");
            }
        });

        btnRetourner.addActionListener(e -> {
            try {
                int idAbonne = Integer.parseInt(txtAbonne.getText().trim());
                int idCassette = Integer.parseInt(txtCassette.getText().trim());
                if (JOptionPane.showConfirmDialog(this, "Confirmer le retour ?") == JOptionPane.YES_OPTION) {
                    locationDAO.retourner(idAbonne, idCassette);
                    model.setRowCount(0);
                    for (Location l : locationDAO.getLocationsAbonne(idAbonne))
                        model.addRow(new Object[]{l.getNumAbonne(), l.getNumCassette(), l.getDateLocation()});
                    txtCassette.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vérifiez les champs ID !");
            }
        });

        JPanel centre = new JPanel(new BorderLayout(0, 10));
        centre.setBackground(CARD_BG);
        centre.add(panelInfo, BorderLayout.NORTH);
        centre.add(new JScrollPane(table), BorderLayout.CENTER);

        card.add(form, BorderLayout.NORTH);
        card.add(centre, BorderLayout.CENTER);
        panelContenu.add(card, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    // ─── UTILITAIRES ───
    private JTable creerTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(TEXT_DARK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(SIDEBAR_BG);
        header.setForeground(TEXT_LIGHT);
        header.setPreferredSize(new Dimension(100, 40));

        return table;
    }

    private JButton creerBoutonAction(String texte, Color couleur) {
        JButton btn = new JButton(texte);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(couleur);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(7, 15, 7, 15));
        return btn;
    }

    private void deconnecter() {
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vous déconnecter ?",
            "Déconnexion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
            SwingUtilities.invokeLater(FenetreConnexion::new);
        }
    }
}