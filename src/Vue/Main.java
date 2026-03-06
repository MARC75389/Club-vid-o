package Vue;

import Connexion.Connexion;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection con = Connexion.getConnexion();
        if (con != null) {
            System.out.println("La connexion fonctionne !");
           
        }
    }
}