package Dao;

import Connexion.Connexion;
import modele.Cassette;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CassetteDAO {

    private Connection connection;

    public CassetteDAO() {
        this.connection = Connexion.getConnexion();
    }

    // Ajouter une cassette
    public void ajouter(Cassette cassette) {
        String sql = "INSERT INTO CASSETTE (date_achat, titre, auteur, duree, prix, num_categorie) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(cassette.getDateAchat().getTime()));
            ps.setString(2, cassette.getTitre());
            ps.setString(3, cassette.getAuteur());
            ps.setInt(4, cassette.getDuree());
            ps.setDouble(5, cassette.getPrix());
            ps.setInt(6, cassette.getNumCategorie());
            ps.executeUpdate();
            System.out.println("Cassette ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout cassette : " + e.getMessage());
        }
    }

    // Afficher toutes les cassettes
    public List<Cassette> getTout() {
        List<Cassette> liste = new ArrayList<>();
        String sql = "SELECT * FROM CASSETTE";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Cassette c = new Cassette();
                c.setNumCassette(rs.getInt("num_cassette"));
                c.setDateAchat(rs.getDate("date_achat"));
                c.setTitre(rs.getString("titre"));
                c.setAuteur(rs.getString("auteur"));
                c.setDuree(rs.getInt("duree"));
                c.setPrix(rs.getDouble("prix"));
                c.setNumCategorie(rs.getInt("num_categorie"));
                liste.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur affichage cassettes : " + e.getMessage());
        }
        return liste;
    }

    // Modifier une cassette
    public void modifier(Cassette cassette) {
        String sql = "UPDATE CASSETTE SET date_achat=?, titre=?, auteur=?, duree=?, prix=?, num_categorie=? WHERE num_cassette=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(cassette.getDateAchat().getTime()));
            ps.setString(2, cassette.getTitre());
            ps.setString(3, cassette.getAuteur());
            ps.setInt(4, cassette.getDuree());
            ps.setDouble(5, cassette.getPrix());
            ps.setInt(6, cassette.getNumCategorie());
            ps.setInt(7, cassette.getNumCassette());
            ps.executeUpdate();
            System.out.println("Cassette modifiée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur modification cassette : " + e.getMessage());
        }
    }

    // Supprimer une cassette
    public void supprimer(int numCassette) {
        String sql = "DELETE FROM CASSETTE WHERE num_cassette=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numCassette);
            ps.executeUpdate();
            System.out.println("Cassette supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur suppression cassette : " + e.getMessage());
        }
    }

    // Chercher une cassette par ID
    public Cassette getParId(int numCassette) {
        Cassette cassette = null;
        String sql = "SELECT * FROM CASSETTE WHERE num_cassette=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numCassette);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cassette = new Cassette();
                cassette.setNumCassette(rs.getInt("num_cassette"));
                cassette.setDateAchat(rs.getDate("date_achat"));
                cassette.setTitre(rs.getString("titre"));
                cassette.setAuteur(rs.getString("auteur"));
                cassette.setDuree(rs.getInt("duree"));
                cassette.setPrix(rs.getDouble("prix"));
                cassette.setNumCategorie(rs.getInt("num_categorie"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur recherche cassette : " + e.getMessage());
        }
        return cassette;
    }
}