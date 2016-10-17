
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
            DonneesSimulation DS = LecteurDonnees.lire("cartes/desertOfDeath-20x20.map");
            Carte map = DS.getCarte();
            System.out.println("Map : "+map.getNbLignes()+"x"+map.getNbColonnes()+", tailleCases : "+map.getTailleCases());
            System.out.println(map.getCase(7,3));
            Affichage aff = new Affichage(DS, 30);
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
    }
}
