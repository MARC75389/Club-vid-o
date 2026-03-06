package Vue;

import Connexion.Connexion;
import javax.swing.*;
import java.awt.*;

public class FenetreConnexion extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtMotDePasse;
    private JComboBox<String> comboRole;

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public FenetreConnexion() {
        setTitle("Club Vidéo - Connexion");
        setSize(420, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        // ── Header ──
        JPanel panelNord = new JPanel(new BorderLayout());
        panelNord.setBackground(new Color(15, 23, 42));
        panelNord.setPreferredSize(new Dimension(420, 90));

        JLabel titre = new JLabel("🎬  CLUB VIDÉO", SwingConstants.CENTER);
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(Color.WHITE);

        JLabel sousTitre = new JLabel("Bienvenue — Connectez-vous", SwingConstants.CENTER);
        sousTitre.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        sousTitre.setForeground(new Color(148, 163, 184));

        panelNord.add(titre, BorderLayout.CENTER);
        panelNord.add(sousTitre, BorderLayout.SOUTH);
        panel.add(panelNord, BorderLayout.NORTH);

        // ── Formulaire ──
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 15));
        panelForm.setBorder(BorderFactory.createEmptyBorder(25, 40, 10, 40));
        panelForm.setBackground(Color.WHITE);

        txtLogin = new JTextField();
        txtMotDePasse = new JPasswordField();
        comboRole = new JComboBox<>(new String[]{"Administrateur", "Client"});
        comboRole.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel lblRole = new JLabel("Rôle :");
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JLabel lblLogin = new JLabel("Login :");
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JLabel lblPassword = new JLabel("Mot de passe :");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));

        panelForm.add(lblRole); panelForm.add(comboRole);
        panelForm.add(lblLogin); panelForm.add(txtLogin);
        panelForm.add(lblPassword); panelForm.add(txtMotDePasse);

        JButton btnConnexion = new JButton("Se connecter");
        btnConnexion.setBackground(new Color(15, 23, 42));
        btnConnexion.setForeground(Color.WHITE);
        btnConnexion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConnexion.setFocusPainted(false);
        btnConnexion.setBorderPainted(false);
        btnConnexion.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelForm.add(new JLabel());
        panelForm.add(btnConnexion);

        panel.add(panelForm, BorderLayout.CENTER);

        // ── Bas : S'abonner ──
        JPanel panelBas = new JPanel(new BorderLayout());
        panelBas.setBackground(Color.WHITE);
        panelBas.setBorder(BorderFactory.createEmptyBorder(5, 40, 15, 40));

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(203, 213, 225));

        JPanel panelAbonner = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        panelAbonner.setBackground(Color.WHITE);

        JLabel lblPasDeCompte = new JLabel("Pas encore abonné ?");
        lblPasDeCompte.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPasDeCompte.setForeground(new Color(100, 116, 139));

        JButton btnSabonner = new JButton("S'abonner maintenant");
        btnSabonner.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSabonner.setBackground(new Color(34, 197, 94));
        btnSabonner.setForeground(Color.WHITE);
        btnSabonner.setFocusPainted(false);
        btnSabonner.setBorderPainted(false);
        btnSabonner.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSabonner.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));

        panelAbonner.add(lblPasDeCompte);
        panelAbonner.add(btnSabonner);

        panelBas.add(sep, BorderLayout.NORTH);
        panelBas.add(panelAbonner, BorderLayout.CENTER);

        // ── Footer ──
        JLabel footer = new JLabel("HIT-T — Projet Club Vidéo", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        footer.setForeground(Color.GRAY);
        footer.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        JPanel panelSud = new JPanel(new BorderLayout());
        panelSud.setBackground(Color.WHITE);
        panelSud.add(panelBas, BorderLayout.NORTH);
        panelSud.add(footer, BorderLayout.SOUTH);

        panel.add(panelSud, BorderLayout.SOUTH);
        add(panel);

        // ── Actions ──
        btnConnexion.addActionListener(e -> verifierConnexion());
        txtMotDePasse.addActionListener(e -> verifierConnexion());
        btnSabonner.addActionListener(e -> {
            dispose();
            new FenetreInscription();
        });

        comboRole.addActionListener(e -> {
            txtLogin.setText("");
            txtMotDePasse.setText("");
        });

        setVisible(true);
    }

    private void verifierConnexion() {
        String login = txtLogin.getText().trim();
        String motDePasse = new String(txtMotDePasse.getPassword());
        String role = (String) comboRole.getSelectedItem();

        if (role.equals("Administrateur")) {
            if (login.equals(ADMIN_LOGIN) && motDePasse.equals(ADMIN_PASSWORD)) {
                JOptionPane.showMessageDialog(this, "Bienvenue Administrateur ! 👤");
                dispose();
                new Fenetre("admin");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Login ou mot de passe incorrect !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                txtMotDePasse.setText("");
            }
        } else {
            // Connexion client avec son login créé lors de l'inscription
            Dao.AbonneDAO abonneDAO = new Dao.AbonneDAO();
            	modele.Abonne abonne = abonneDAO.getParLogin(login, motDePasse);
            if (abonne != null) {
                JOptionPane.showMessageDialog(this, "Bienvenue " + abonne.getNomAbonne() + " ! 🎬");
                dispose();
                new Fenetre("client");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Login ou mot de passe incorrect !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                txtMotDePasse.setText("");
            }
        }
    }

    public static void main(String[] args) {
        Connexion.getConnexion();
        SwingUtilities.invokeLater(FenetreConnexion::new);
    }
}