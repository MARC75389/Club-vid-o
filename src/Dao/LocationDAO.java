package Dao;

import Connexion.Connexion;
import modele.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    private Connection connection;
    private AbonneDAO abonneDAO;

    public LocationDAO() {
        this.connection = Connexion.getConnexion();
        this.abonneDAO = new AbonneDAO();
    }

    // Ajouter ou mettre à jour une location (RG2)
    public void louer(Location location) {
        // Vérifier si l'abonné peut encore louer (RG1)
        if (!abonneDAO.peutLouer(location.getNumAbonne())) {
            System.out.println("Erreur : l'abonné a déjà 3 cassettes en location !");
            return;
        }

        // Vérifier si la location existe déjà (RG2)
        String sqlCheck = "SELECT COUNT(*) FROM LOCATION WHERE num_abonne=? AND num_cassette=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlCheck);
            ps.setInt(1, location.getNumAbonne());
            ps.setInt(2, location.getNumCassette());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // UPDATE si la location existe déjà
                String sqlUpdate = "UPDATE LOCATION SET date_location=? WHERE num_abonne=? AND num_cassette=?";
                PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate);
                psUpdate.setDate(1, new java.sql.Date(location.getDateLocation().getTime()));
                psUpdate.setInt(2, location.getNumAbonne());
                psUpdate.setInt(3, location.getNumCassette());
                psUpdate.executeUpdate();
                System.out.println("Date de location mise à jour !");
            } else {
                // INSERT si nouvelle location
                String sqlInsert = "INSERT INTO LOCATION (num_abonne, num_cassette, date_location) VALUES (?, ?, ?)";
                PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
                psInsert.setInt(1, location.getNumAbonne());
                psInsert.setInt(2, location.getNumCassette());
                psInsert.setDate(3, new java.sql.Date(location.getDateLocation().getTime()));
                psInsert.executeUpdate();

                // Incrémenter nombre_location de l'abonné
                String sqlIncr = "UPDATE ABONNE SET nombre_location = nombre_location + 1 WHERE num_abonne=?";
                PreparedStatement psIncr = connection.prepareStatement(sqlIncr);
                psIncr.setInt(1, location.getNumAbonne());
                psIncr.executeUpdate();
                System.out.println("Location enregistrée avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur location : " + e.getMessage());
        }
    }

    // Retourner une cassette (RG5)
    public void retourner(int numAbonne, int numCassette) {
        String sql = "DELETE FROM LOCATION WHERE num_abonne=? AND num_cassette=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numAbonne);
            ps.setInt(2, numCassette);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                // Décrémenter nombre_location de l'abonné
                String sqlDecr = "UPDATE ABONNE SET nombre_location = nombre_location - 1 WHERE num_abonne=?";
                PreparedStatement psDecr = connection.prepareStatement(sqlDecr);
                psDecr.setInt(1, numAbonne);
                psDecr.executeUpdate();
                System.out.println("Retour enregistré avec succès !");
            } else {
                System.out.println("Aucune location trouvée pour cet abonné et cette cassette.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur retour : " + e.getMessage());
        }
    }

    // Afficher toutes les locations
    public List<Location> getTout() {
        List<Location> liste = new ArrayList<>();
        String sql = "SELECT * FROM LOCATION";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Location l = new Location();
                l.setNumAbonne(rs.getInt("num_abonne"));
                l.setNumCassette(rs.getInt("num_cassette"));
                l.setDateLocation(rs.getDate("date_location"));
                liste.add(l);
            }
        } catch (SQLException e) {
            System.out.println("Erreur affichage locations : " + e.getMessage());
        }
        return liste;
    }

    // Afficher les locations en cours d'un abonné
    public List<Location> getLocationsAbonne(int numAbonne) {
        List<Location> liste = new ArrayList<>();
        String sql = "SELECT * FROM LOCATION WHERE num_abonne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numAbonne);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location l = new Location();
                l.setNumAbonne(rs.getInt("num_abonne"));
                l.setNumCassette(rs.getInt("num_cassette"));
                l.setDateLocation(rs.getDate("date_location"));
                liste.add(l);
            }
        } catch (SQLException e) {
            System.out.println("Erreur locations abonné : " + e.getMessage());
        }
        return liste;
    }

	public List<Location> getLocationsAbonne1(int idAb3) {
		// TODO Auto-generated method stub
		return null;
	}
}