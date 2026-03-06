package Dao;

import Connexion.Connexion;
import modele.Abonne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonneDAO {

    private Connection connection;

    public AbonneDAO() {
        this.connection = Connexion.getConnexion();
    }

    // Ajouter un abonné
    public void ajouter(Abonne abonne) {
        String sql = "INSERT INTO ABONNE (nom_abonne, adresse_abonne, date_abonnement, date_entree, nombre_location) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, abonne.getNomAbonne());
            ps.setString(2, abonne.getAdresseAbonne());
            ps.setDate(3, new java.sql.Date(abonne.getDateAbonnement().getTime()));
            ps.setDate(4, new java.sql.Date(abonne.getDateEntree().getTime()));
            ps.setInt(5, abonne.getNombreLocation());
            ps.executeUpdate();
            System.out.println("Abonné ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout abonné : " + e.getMessage());
        }
    }

    // Ajouter un abonné avec login et mot de passe
    public boolean ajouterAvecLogin(Abonne abonne, String login, String motDePasse) {
        String sql = "INSERT INTO ABONNE (nom_abonne, adresse_abonne, date_abonnement, date_entree, nombre_location, login, mot_de_passe) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, abonne.getNomAbonne());
            ps.setString(2, abonne.getAdresseAbonne());
            ps.setDate(3, new java.sql.Date(abonne.getDateAbonnement().getTime()));
            ps.setDate(4, new java.sql.Date(abonne.getDateEntree().getTime()));
            ps.setInt(5, abonne.getNombreLocation());
            ps.setString(6, login);
            ps.setString(7, motDePasse);
            ps.executeUpdate();
            System.out.println("Abonné inscrit avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("ERREUR INSCRIPTION : " + e.getMessage());
            return false;
        }
    }

    // Afficher tous les abonnés
    public List<Abonne> getTout() {
        List<Abonne> liste = new ArrayList<>();
        String sql = "SELECT * FROM ABONNE";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Abonne a = new Abonne();
                a.setNumAbonne(rs.getInt("num_abonne"));
                a.setNomAbonne(rs.getString("nom_abonne"));
                a.setAdresseAbonne(rs.getString("adresse_abonne"));
                a.setDateAbonnement(rs.getDate("date_abonnement"));
                a.setDateEntree(rs.getDate("date_entree"));
                a.setNombreLocation(rs.getInt("nombre_location"));
                liste.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erreur affichage abonnés : " + e.getMessage());
        }
        return liste;
    }

    // Modifier un abonné
    public void modifier(Abonne abonne) {
        String sql = "UPDATE ABONNE SET nom_abonne=?, adresse_abonne=?, date_abonnement=?, date_entree=?, nombre_location=? WHERE num_abonne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, abonne.getNomAbonne());
            ps.setString(2, abonne.getAdresseAbonne());
            ps.setDate(3, new java.sql.Date(abonne.getDateAbonnement().getTime()));
            ps.setDate(4, new java.sql.Date(abonne.getDateEntree().getTime()));
            ps.setInt(5, abonne.getNombreLocation());
            ps.setInt(6, abonne.getNumAbonne());
            ps.executeUpdate();
            System.out.println("Abonné modifié avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur modification abonné : " + e.getMessage());
        }
    }

    // Supprimer un abonné
    public void supprimer(int numAbonne) {
        String sql = "DELETE FROM ABONNE WHERE num_abonne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numAbonne);
            ps.executeUpdate();
            System.out.println("Abonné supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur suppression abonné : " + e.getMessage());
        }
    }

    // Chercher un abonné par ID
    public Abonne getParId(int numAbonne) {
        Abonne abonne = null;
        String sql = "SELECT * FROM ABONNE WHERE num_abonne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numAbonne);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                abonne = new Abonne();
                abonne.setNumAbonne(rs.getInt("num_abonne"));
                abonne.setNomAbonne(rs.getString("nom_abonne"));
                abonne.setAdresseAbonne(rs.getString("adresse_abonne"));
                abonne.setDateAbonnement(rs.getDate("date_abonnement"));
                abonne.setDateEntree(rs.getDate("date_entree"));
                abonne.setNombreLocation(rs.getInt("nombre_location"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur recherche abonné : " + e.getMessage());
        }
        return abonne;
    }

    // Chercher un abonné par login et mot de passe
    public Abonne getParLogin(String login, String motDePasse) {
        Abonne abonne = null;
        String sql = "SELECT * FROM ABONNE WHERE login=? AND mot_de_passe=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, motDePasse);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                abonne = new Abonne();
                abonne.setNumAbonne(rs.getInt("num_abonne"));
                abonne.setNomAbonne(rs.getString("nom_abonne"));
                abonne.setAdresseAbonne(rs.getString("adresse_abonne"));
                abonne.setDateAbonnement(rs.getDate("date_abonnement"));
                abonne.setDateEntree(rs.getDate("date_entree"));
                abonne.setNombreLocation(rs.getInt("nombre_location"));
                abonne.setLogin(rs.getString("login"));
                abonne.setMotDePasse(rs.getString("mot_de_passe"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur login : " + e.getMessage());
        }
        return abonne;
    }

    // Vérifier si l'abonné peut encore louer (max 3)
    public boolean peutLouer(int numAbonne) {
        Abonne abonne = getParId(numAbonne);
        if (abonne != null) {
            return abonne.getNombreLocation() < 3;
        }
        return false;
    }
}