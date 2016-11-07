
import gui.GUISimulator;

import io.LecteurDonnees;
import objects.*;
import animation.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestCarte {

    public static void main(String[] args) {
        try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation DS = LecteurDonnees.lire("cartes/carteSujet.map");
            Carte map = DS.getCarte();
            System.out.println("Map : "+map.getNbLignes()+"x"+map.getNbColonnes()+", tailleCases : "+map.getTailleCases());
            System.out.println(map.getCase(7,3));
            Simulateur aff = new Simulateur(gui, DS);
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
    }
}
