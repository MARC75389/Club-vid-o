package Dao;

import Connexion.Connexion;
import modele.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {

    private Connection connection;

    public CategorieDAO() {
        this.connection = Connexion.getConnexion();
    }

    // Ajouter une catégorie
    public void ajouter(Categorie categorie) {
        String sql = "INSERT INTO CATEGORIE (libelle_categorie) VALUES (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categorie.getLibelleCategorie());
            ps.executeUpdate();
            System.out.println("Catégorie ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout catégorie : " + e.getMessage());
        }
    }

    // Afficher toutes les catégories
    public List<Categorie> getTout() {
        List<Categorie> liste = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIE";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setNumCategorie(rs.getInt("num_categorie"));
                c.setLibelleCategorie(rs.getString("libelle_categorie"));
                liste.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur affichage catégories : " + e.getMessage());
        }
        return liste;
    }

    // Modifier une catégorie
    public void modifier(Categorie categorie) {
        String sql = "UPDATE CATEGORIE SET libelle_categorie=? WHERE num_categorie=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categorie.getLibelleCategorie());
            ps.setInt(2, categorie.getNumCategorie());
            ps.executeUpdate();
            System.out.println("Catégorie modifiée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur modification catégorie : " + e.getMessage());
        }
    }

    // Supprimer une catégorie
    public void supprimer(int numCategorie) {
        String sql = "DELETE FROM CATEGORIE WHERE num_categorie=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numCategorie);
            ps.executeUpdate();
            System.out.println("Catégorie supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur suppression catégorie : " + e.getMessage());
        }
    }

    // Chercher une catégorie par ID
    public Categorie getParId(int numCategorie) {
        Categorie categorie = null;
        String sql = "SELECT * FROM CATEGORIE WHERE num_categorie=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numCategorie);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categorie = new Categorie();
                categorie.setNumCategorie(rs.getInt("num_categorie"));
                categorie.setLibelleCategorie(rs.getString("libelle_categorie"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur recherche catégorie : " + e.getMessage());
        }
        return categorie;
    }
}