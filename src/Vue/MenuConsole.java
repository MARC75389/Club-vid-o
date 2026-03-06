package Vue;

import Dao.*;
import modele.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    private static Scanner scanner = new Scanner(System.in);
    private static CategorieDAO categorieDAO = new CategorieDAO();
    private static CassetteDAO cassetteDAO = new CassetteDAO();
    private static AbonneDAO abonneDAO = new AbonneDAO();
    private static LocationDAO locationDAO = new LocationDAO();

    public static void main(String[] args) {
        int choix;
        do {
            System.out.println("\n=============================");
            System.out.println("   CLUB VIDEO - MENU PRINCIPAL");
            System.out.println("=============================");
            System.out.println("1. Gérer les catégories");
            System.out.println("2. Gérer les cassettes");
            System.out.println("3. Gérer les abonnés");
            System.out.println("4. Gérer les locations");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1: menuCategorie(); break;
                case 2: menuCassette(); break;
                case 3: menuAbonne(); break;
                case 4: menuLocation(); break;
                case 0: System.out.println("Au revoir !"); break;
                default: System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    // ─── MENU CATEGORIE ───
    static void menuCategorie() {
        System.out.println("\n--- GESTION DES CATEGORIES ---");
        System.out.println("1. Ajouter une catégorie");
        System.out.println("2. Afficher toutes les catégories");
        System.out.println("3. Modifier une catégorie");
        System.out.println("4. Supprimer une catégorie");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                System.out.print("Libellé : ");
                String libelle = scanner.nextLine();
                categorieDAO.ajouter(new Categorie(0, libelle));
                break;
            case 2:
                List<Categorie> categories = categorieDAO.getTout();
                for (Categorie c : categories)
                    System.out.println(c);
                break;
            case 3:
                System.out.print("ID catégorie à modifier : ");
                int idCat = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nouveau libellé : ");
                String newLibelle = scanner.nextLine();
                categorieDAO.modifier(new Categorie(idCat, newLibelle));
                break;
            case 4:
                System.out.print("ID catégorie à supprimer : ");
                int idSupp = scanner.nextInt();
                categorieDAO.supprimer(idSupp);
                break;
        }
    }

    // ─── MENU CASSETTE ───
    static void menuCassette() {
        System.out.println("\n--- GESTION DES CASSETTES ---");
        System.out.println("1. Ajouter une cassette");
        System.out.println("2. Afficher toutes les cassettes");
        System.out.println("3. Modifier une cassette");
        System.out.println("4. Supprimer une cassette");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                Cassette c = new Cassette();
                System.out.print("Titre : ");
                c.setTitre(scanner.nextLine());
                System.out.print("Auteur : ");
                c.setAuteur(scanner.nextLine());
                System.out.print("Durée (min) : ");
                c.setDuree(scanner.nextInt());
                System.out.print("Prix : ");
                c.setPrix(scanner.nextDouble());
                System.out.print("ID Catégorie : ");
                c.setNumCategorie(scanner.nextInt());
                c.setDateAchat(new Date());
                cassetteDAO.ajouter(c);
                break;
            case 2:
                List<Cassette> cassettes = cassetteDAO.getTout();
                for (Cassette cas : cassettes)
                    System.out.println(cas);
                break;
            case 3:
                System.out.print("ID cassette à modifier : ");
                int idCas = scanner.nextInt();
                scanner.nextLine();
                Cassette cas = cassetteDAO.getParId(idCas);
                if (cas != null) {
                    System.out.print("Nouveau titre : ");
                    cas.setTitre(scanner.nextLine());
                    System.out.print("Nouvel auteur : ");
                    cas.setAuteur(scanner.nextLine());
                    System.out.print("Nouvelle durée : ");
                    cas.setDuree(scanner.nextInt());
                    System.out.print("Nouveau prix : ");
                    cas.setPrix(scanner.nextDouble());
                    cassetteDAO.modifier(cas);
                }
                break;
            case 4:
                System.out.print("ID cassette à supprimer : ");
                int idSupp = scanner.nextInt();
                cassetteDAO.supprimer(idSupp);
                break;
        }
    }

    // ─── MENU ABONNE ───
    static void menuAbonne() {
        System.out.println("\n--- GESTION DES ABONNES ---");
        System.out.println("1. Ajouter un abonné");
        System.out.println("2. Afficher tous les abonnés");
        System.out.println("3. Modifier un abonné");
        System.out.println("4. Supprimer un abonné");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                Abonne a = new Abonne();
                System.out.print("Nom : ");
                a.setNomAbonne(scanner.nextLine());
                System.out.print("Adresse : ");
                a.setAdresseAbonne(scanner.nextLine());
                a.setDateAbonnement(new Date());
                a.setDateEntree(new Date());
                a.setNombreLocation(0);
                abonneDAO.ajouter(a);
                break;
            case 2:
                List<Abonne> abonnes = abonneDAO.getTout();
                for (Abonne ab : abonnes)
                    System.out.println(ab);
                break;
            case 3:
                System.out.print("ID abonné à modifier : ");
                int idAb = scanner.nextInt();
                scanner.nextLine();
                Abonne ab = abonneDAO.getParId(idAb);
                if (ab != null) {
                    System.out.print("Nouveau nom : ");
                    ab.setNomAbonne(scanner.nextLine());
                    System.out.print("Nouvelle adresse : ");
                    ab.setAdresseAbonne(scanner.nextLine());
                    abonneDAO.modifier(ab);
                }
                break;
            case 4:
                System.out.print("ID abonné à supprimer : ");
                int idSupp = scanner.nextInt();
                abonneDAO.supprimer(idSupp);
                break;
        }
    }

    // ─── MENU LOCATION ───
    static void menuLocation() {
        System.out.println("\n--- GESTION DES LOCATIONS ---");
        System.out.println("1. Enregistrer une location");
        System.out.println("2. Enregistrer un retour");
        System.out.println("3. Afficher toutes les locations");
        System.out.println("4. Afficher les locations d'un abonné");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                System.out.print("ID abonné : ");
                int idAb = scanner.nextInt();
                System.out.print("ID cassette : ");
                int idCas = scanner.nextInt();
                locationDAO.louer(new Location(idAb, idCas, new Date()));
                break;
            case 2:
                System.out.print("ID abonné : ");
                int idAb2 = scanner.nextInt();
                System.out.print("ID cassette : ");
                int idCas2 = scanner.nextInt();
                locationDAO.retourner(idAb2, idCas2);
                break;
            case 3:
                List<Location> locations = locationDAO.getTout();
                for (Location l : locations)
                    System.out.println(l);
                break;
            case 4:
                System.out.print("ID abonné : ");
                int idAb3 = scanner.nextInt();
                List<Location> locAbonne = locationDAO.getLocationsAbonne(idAb3);
                for (Location l : locAbonne)
                    System.out.println(l);
                break;
        }
    }
}