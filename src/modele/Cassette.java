package modele;

import java.util.Date;

public class Cassette {

    private int numCassette;
    private Date dateAchat;
    private String titre;
    private String auteur;
    private int duree;
    private double prix;
    private int numCategorie;

    public Cassette() {}

    public Cassette(int numCassette, Date dateAchat, String titre, String auteur, int duree, double prix, int numCategorie) {
        this.numCassette = numCassette;
        this.dateAchat = dateAchat;
        this.titre = titre;
        this.auteur = auteur;
        this.duree = duree;
        this.prix = prix;
        this.numCategorie = numCategorie;
    }

    public int getNumCassette() { return numCassette; }
    public void setNumCassette(int numCassette) { this.numCassette = numCassette; }

    public Date getDateAchat() { return dateAchat; }
    public void setDateAchat(Date dateAchat) { this.dateAchat = dateAchat; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getNumCategorie() { return numCategorie; }
    public void setNumCategorie(int numCategorie) { this.numCategorie = numCategorie; }

    @Override
    public String toString() {
        return "Cassette{id=" + numCassette + ", titre=" + titre + ", auteur=" + auteur + ", duree=" + duree + "min, prix=" + prix + "}";
    }
}