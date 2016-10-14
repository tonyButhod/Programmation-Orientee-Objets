
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import io.LecteurDonnees;
import objects.*;
import animation.Affichage;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestCarte {

    public static void main(String[] args) {
        try {
            Carte map = LecteurDonnees.lire("cartes/carteSujet.map");
            System.out.println("Map : "+map.getNbLignes()+"x"+map.getNbColonnes()+", tailleCases : "+map.getTailleCases());
            System.out.println(map.getCase(7,3));
            DonneesSimulation DS = new DonneesSimulation(map, null, null);
            System.out.println("Ok !");
            Affichage aff = new Affichage(DS, 50);
            System.out.println("C'est bon !");
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
    }
}
