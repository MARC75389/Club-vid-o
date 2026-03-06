package modele;

import java.util.Date;

public class Location {

    private int numAbonne;
    private int numCassette;
    private Date dateLocation;

    public Location() {}

    public Location(int numAbonne, int numCassette, Date dateLocation) {
        this.numAbonne = numAbonne;
        this.numCassette = numCassette;
        this.dateLocation = dateLocation;
    }

    public int getNumAbonne() { return numAbonne; }
    public void setNumAbonne(int numAbonne) { this.numAbonne = numAbonne; }

    public int getNumCassette() { return numCassette; }
    public void setNumCassette(int numCassette) { this.numCassette = numCassette; }

    public Date getDateLocation() { return dateLocation; }
    public void setDateLocation(Date dateLocation) { this.dateLocation = dateLocation; }

    @Override
    public String toString() {
        return "Location{abonne=" + numAbonne + ", cassette=" + numCassette + ", date=" + dateLocation + "}";
    }
}