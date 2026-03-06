package modele;

public class Categorie {

    private int numCategorie;
    private String libelleCategorie;

    public Categorie() {}

    public Categorie(int numCategorie, String libelleCategorie) {
        this.numCategorie = numCategorie;
        this.libelleCategorie = libelleCategorie;
    }

    public int getNumCategorie() { return numCategorie; }
    public void setNumCategorie(int numCategorie) { this.numCategorie = numCategorie; }

    public String getLibelleCategorie() { return libelleCategorie; }
    public void setLibelleCategorie(String libelleCategorie) { this.libelleCategorie = libelleCategorie; }

    @Override
    public String toString() {
        return "Categorie{id=" + numCategorie + ", libelle=" + libelleCategorie + "}";
    }
}