package Vue;

import Dao.AbonneDAO;
import modele.Abonne;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FenetreInscription extends JFrame {

    private JTextField txtNom, txtAdresse, txtLogin;
    private JPasswordField txtMotDePasse, txtConfirmer;
    private AbonneDAO abonneDAO = new AbonneDAO();

    public FenetreInscription() {
        setTitle("Club Vidéo - S'abonner");
        setSize(450, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        // ── Header ──
        JPanel panelNord = new JPanel(new BorderLayout());
        panelNord.setBackground(new Color(15, 23, 42));
        panelNord.setPreferredSize(new Dimension(450, 90));

        JLabel titre = new JLabel("🎬  CLUB VIDÉO", SwingConstants.CENTER);
        titre.setFont(new Font("Georgia", Font.BOLD, 26));
        titre.setForeground(Color.WHITE);

        JLabel sousTitre = new JLabel("Créer votre compte abonné", SwingConstants.CENTER);
        sousTitre.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        sousTitre.setForeground(new Color(148, 163, 184));

        panelNord.add(titre, BorderLayout.CENTER);
        panelNord.add(sousTitre, BorderLayout.SOUTH);
        panel.add(panelNord, BorderLayout.NORTH);

        // ── Formulaire ──
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 15));
        panelForm.setBorder(BorderFactory.createEmptyBorder(25, 40, 15, 40));
        panelForm.setBackground(Color.WHITE);

        txtNom = new JTextField();
        txtAdresse = new JTextField();
        txtLogin = new JTextField();
        txtMotDePasse = new JPasswordField();
        txtConfirmer = new JPasswordField();

        ajouterLabel(panelForm, "Nom complet :");
        panelForm.add(txtNom);
        ajouterLabel(panelForm, "Adresse :");
        panelForm.add(txtAdresse);
        ajouterLabel(panelForm, "Login :");
        panelForm.add(txtLogin);
        ajouterLabel(panelForm, "Mot de passe :");
        panelForm.add(txtMotDePasse);
        ajouterLabel(panelForm, "Confirmer mdp :");
        panelForm.add(txtConfirmer);

        JButton btnInscrire = new JButton("✅ Créer mon compte");
        btnInscrire.setBackground(new Color(34, 197, 94));
        btnInscrire.setForeground(Color.WHITE);
        btnInscrire.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnInscrire.setFocusPainted(false);
        btnInscrire.setBorderPainted(false);
        btnInscrire.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnRetour = new JButton("← Retour");
        btnRetour.setBackground(new Color(100, 116, 139));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRetour.setFocusPainted(false);
        btnRetour.setBorderPainted(false);
        btnRetour.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelForm.add(btnRetour);
        panelForm.add(btnInscrire);

        panel.add(panelForm, BorderLayout.CENTER);

        // ── Footer ──
        JLabel footer = new JLabel("HIT-T — Projet Club Vidéo", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        footer.setForeground(Color.GRAY);
        footer.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        footer.setBackground(Color.WHITE);
        footer.setOpaque(true);
        panel.add(footer, BorderLayout.SOUTH);

        add(panel);

        // ── Actions ──
        btnInscrire.addActionListener(e -> inscrire());
        btnRetour.addActionListener(e -> {
            dispose();
            new FenetreConnexion();
        });

        setVisible(true);
    }

    private void ajouterLabel(JPanel panel, String texte) {
        JLabel lbl = new JLabel(texte);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lbl);
    }

    private void inscrire() {
        String nom = txtNom.getText().trim();
        String adresse = txtAdresse.getText().trim();
        String login = txtLogin.getText().trim();
        String mdp = new String(txtMotDePasse.getPassword());
        String confirmer = new String(txtConfirmer.getPassword());

        // Validations
        if (nom.isEmpty() || adresse.isEmpty() || login.isEmpty() || mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tous les champs sont obligatoires !", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!mdp.equals(confirmer)) {
            JOptionPane.showMessageDialog(this, 
                "Les mots de passe ne correspondent pas !", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mdp.length() < 4) {
            JOptionPane.showMessageDialog(this, 
                "Le mot de passe doit avoir au moins 4 caractères !", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Créer l'abonné
        Abonne abonne = new Abonne();
        abonne.setNomAbonne(nom);
        abonne.setAdresseAbonne(adresse);
        abonne.setDateAbonnement(new Date());
        abonne.setDateEntree(new Date());
        abonne.setNombreLocation(0);

        boolean succes = abonneDAO.ajouterAvecLogin(abonne, login, mdp);

        if (succes) {
            JOptionPane.showMessageDialog(this,
                "✅ Compte créé avec succès !\n" +
                "Votre login : " + login + "\n" +
                "Vous pouvez maintenant vous connecter.",
                "Inscription réussie",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new FenetreConnexion();
        } else {
            JOptionPane.showMessageDialog(this,
                "Ce login est déjà utilisé, choisissez-en un autre !",
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}