package modele;

import java.util.Date;

public class Abonne {

    private int numAbonne;
    private String nomAbonne;
    private String adresseAbonne;
    private Date dateAbonnement;
    private Date dateEntree;
    private int nombreLocation;
    private String login;
    private String motDePasse;

    public Abonne() {}

    public Abonne(int numAbonne, String nomAbonne, String adresseAbonne,
                  Date dateAbonnement, Date dateEntree, int nombreLocation) {
        this.numAbonne = numAbonne;
        this.nomAbonne = nomAbonne;
        this.adresseAbonne = adresseAbonne;
        this.dateAbonnement = dateAbonnement;
        this.dateEntree = dateEntree;
        this.nombreLocation = nombreLocation;
    }

    public int getNumAbonne() { return numAbonne; }
    public void setNumAbonne(int numAbonne) { this.numAbonne = numAbonne; }

    public String getNomAbonne() { return nomAbonne; }
    public void setNomAbonne(String nomAbonne) { this.nomAbonne = nomAbonne; }

    public String getAdresseAbonne() { return adresseAbonne; }
    public void setAdresseAbonne(String adresseAbonne) { this.adresseAbonne = adresseAbonne; }

    public Date getDateAbonnement() { return dateAbonnement; }
    public void setDateAbonnement(Date dateAbonnement) { this.dateAbonnement = dateAbonnement; }

    public Date getDateEntree() { return dateEntree; }
    public void setDateEntree(Date dateEntree) { this.dateEntree = dateEntree; }

    public int getNombreLocation() { return nombreLocation; }
    public void setNombreLocation(int nombreLocation) { this.nombreLocation = nombreLocation; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    @Override
    public String toString() {
        return "Abonne{id=" + numAbonne + ", nom=" + nomAbonne +
               ", adresse=" + adresseAbonne + ", nbLocation=" + nombreLocation + "}";
    }
}