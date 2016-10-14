
import io.LecteurDonnees;
import objects.Direction;
import objects.NatureTerrain;
import objects.Case;
import objects.Carte;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestCarte {

    public static void main(String[] args) {
        try {
            Carte map = LecteurDonnees.lire("cartes/carteSujet.map");
            System.out.println("Map : "+map.getNbLignes()+"x"+map.getNbColonnes()+", tailleCases : "+map.getTailleCases());
            System.out.println(map.getCase(7,3));
        } finally {
            return;
        }
    }
}
